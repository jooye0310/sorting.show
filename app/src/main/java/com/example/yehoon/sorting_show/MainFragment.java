package com.example.yehoon.sorting_show;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment{

    FloatingActionButton fab;
    FloatingActionButton fab2;
//    BottomBar bottomBar;
    AppBarLayout appBarLayout;

    LogFragment logFragment;
    CodeFragment codeFragment;
//    AlgoDescriptionFragment algoFragment;
    ViewPager viewPager;

    VisualizerController algorithm;

    String startCommand = VisualizerController.COMMAND_START_ALGORITHM;

    public static MainFragment newInstance(String algorithm, DataSet ds) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(VisualizerController.KEY_ALGORITHM, algorithm);
        bundle.putSerializable(VisualizerController.KEY_ALGORITHM + "l", ds);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        appBarLayout = (AppBarLayout) rootView.findViewById(R.id.app_bar);
//        bottomBar = BottomBar.attachShy((CoordinatorLayout) rootView.findViewById(R.id.coordinator), savedInstanceState);
//        bottomBar.noNavBarGoodness();
//        bottomBar.noTabletGoodness();

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab2 = (FloatingActionButton) rootView.findViewById(R.id.fab2);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);


//        bottomBar.setItems(
//                new BottomBarTab(R.drawable.ic_wb_incandescent_white_24dp, "Details"),
//                new BottomBarTab(R.drawable.ic_short_text_white_24dp, "Execution"),
//                new BottomBarTab(R.drawable.ic_code_white_24dp, "Code")
//        );

//        bottomBar.setOnTabClickListener(new OnTabClickListener() {
//            @Override
//            public void onTabSelected(int position) {
//                viewPager.setCurrentItem(position);
//                if (position == 2) {
//                    bottomBar.hide();
//                }
//            }
//
//            @Override
//            public void onTabReSelected(int position) {
//
//            }
//        });

        logFragment = LogFragment.newInstance();
//        codeFragment = CodeFragment.newInstance(getArguments().getString(VisualizerController.KEY_ALGORITHM));
  //      algoFragment = AlgoDescriptionFragment.newInstance(getArguments().getString(VisualizerController.KEY_ALGORITHM));

        setupFragment(getArguments().getString(VisualizerController.KEY_ALGORITHM), getArguments().getSerializable(VisualizerController.KEY_ALGORITHM + "l"));

        return rootView;
    }

    public void setStartCommand(String startCommand) {
        this.startCommand = startCommand;
    }

    public void setupFragment(String algorithmKey, Serializable ds) {

//        viewPager.setOffscreenPageLimit(3);
//        bottomBar.selectTabAtPosition(0, false);
        setupViewPager(viewPager);

//        codeFragment.setCode(algorithmKey);
//        algoFragment.setCodeDesc(algorithmKey);

        assert algorithmKey != null;

        final AlgorithmVisualizer visualizer;

        appBarLayout.removeAllViewsInLayout();

//        View toolbar = LayoutInflater.from(getActivity()).inflate(R.layout.toolbar, appBarLayout, false);
//        appBarLayout.addView(toolbar);

  //      ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar) toolbar);
//        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
//        assert ab != null;
//        ab.setTitle("");
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        fab.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.GONE);


        switch (algorithmKey) {
            case VisualizerController.INSERTION_SORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new InsertionSort();
                algorithm.setSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                setFontSize(10);
                setZoom(50);
                algorithm.setINTERVAL(200);
                algorithm.setBarWidth(10);
                algorithm.setMargin(30);

                ((InsertionSort) algorithm).setData((DataSet) ds);
                break;
            case VisualizerController.QUICKSORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new QuickSort();
                algorithm.setSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                setFontSize(7);
                setZoom(50);
                algorithm.setINTERVAL(1000);
                algorithm.setBarWidth(3);
                ((QuickSort) algorithm).setData((DataSet) ds);
                break;
            case VisualizerController.MERGE_SORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new MergeSort();
                algorithm.setSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                setFontSize(7);
                setZoom(50);
                algorithm.setINTERVAL(100);
                algorithm.setBarWidth(3);
                ((MergeSort)algorithm).setData((DataSet) ds);
                break;
            case VisualizerController.TIM_SORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new TimSort();
                algorithm.setSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                ((TimSort) algorithm).setData((DataSet) ds);
                setFontSize(10);
                setZoom(50);
                break;
            case VisualizerController.RADIX_SORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new RadixSort();
                algorithm.setSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                ((RadixSort) algorithm).setData((DataSet) ds);
                algorithm.setINTERVAL(1000);
                algorithm.setBarWidth(3);
                setFontSize(10);
                setZoom(350);
                break;
            case VisualizerController.DUALPIVOTQUICKSORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new DualPivotQuickSort();
                algorithm.setSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                setFontSize(10);
                setZoom(50);
                algorithm.setINTERVAL(1000);
                algorithm.setBarWidth(3);
                algorithm.setMargin(30);

                ((DualPivotQuickSort) algorithm).setData((DataSet) ds);
                break;
            default:
                visualizer = null;
        }

        algorithm.setCompletionListener(new AlgoCompletionListener() {
            @Override
            public void onAlgoCompleted() {
                fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                if (visualizer != null) {
                    visualizer.onCompleted();
                    fab2.setVisibility(View.VISIBLE);
                }
            }
        });


        VisualizerController.setInterval(Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getString("preference_interval", "500")));
        algorithm.setStarted(false);
        fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        fab2.setImageResource(R.mipmap.ic_code);
        logFragment.clearLog();

//        algorithm.setCompletionListener(new AlgoCompletionListener() {
//            @Override
//            public void onAlgoCompleted() {
//                fab.setImageResource(R.drawable.ic_settings_backup_restore_white_24dp);
//                if (visualizer != null)
//                    visualizer.onCompleted();
//
//            }
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!algorithm.isStarted()) {
                    algorithm.sendMessage(startCommand);
                    fab.setImageResource(R.drawable.ic_pause_white_24dp);
                    logFragment.clearLog();
//                    bottomBar.selectTabAtPosition(1, true);//move to log fragment
                } else {
                    if (algorithm.isPaused()) {
                        algorithm.setPaused(false);
                        fab2.setVisibility(View.GONE);
                        fab.setImageResource(R.drawable.ic_pause_white_24dp);
                    } else {
                        algorithm.setPaused(true);
                        fab2.setVisibility(View.VISIBLE);
                        fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    }
                }
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getContext(), FullCode.class);
                Bundle myData = new Bundle();
                myData.putStringArrayList("code", ((ArrayList<String>)logFragment.getFullLogList()));
                myIntent.putExtras(myData);
                startActivity(myIntent);
            }
        });


//        View shadow = LayoutInflater.from(getActivity()).inflate(R.layout.shadow, appBarLayout, false);
//        appBarLayout.addView(shadow);

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
 //       adapter.addFragment(algoFragment, "Algo");
        adapter.addFragment(logFragment, "Log");
//        adapter.addFragment(codeFragment, "Code");
        viewPager.setAdapter(adapter);
/*
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                bottomBar.selectTabAtPosition(position, false);
//                bottomBar.hide();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
  */  }

    static class Adapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragments = new ArrayList<>();
//        private final List<String> mFragmentTitles = new ArrayList<>();

        private Adapter(FragmentManager fm) {
            super(fm);
        }

        private void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragments.add(fragment);
  //          mFragmentTitles.add(title);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
/*
        @Override
        public CharSequence getPageTitle(int position) {
    //        return mFragmentTitles.get(position);
        }*/
    }


    private void setFontSize(int x){
        algorithm.setFontSize(x);
    }
    private void setZoom(double x){
        algorithm.setZoom(x);
    }


}
