package com.example.yehoon.sorting_show;

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

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment{

    FloatingActionButton fab;
//    BottomBar bottomBar;
    AppBarLayout appBarLayout;

    LogFragment logFragment;
    CodeFragment codeFragment;
//    AlgoDescriptionFragment algoFragment;
    ViewPager viewPager;

    VisualizerController algorithm;

    String startCommand = VisualizerController.COMMAND_START_ALGORITHM;

    public static MainFragment newInstance(String algorithm) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(VisualizerController.KEY_ALGORITHM, algorithm);
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

        setupFragment(getArguments().getString(VisualizerController.KEY_ALGORITHM));

        return rootView;
    }

    public void setStartCommand(String startCommand) {
        this.startCommand = startCommand;
    }

    public void setupFragment(String algorithmKey) {

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


        switch (algorithmKey) {
            case VisualizerController.INSERTION_SORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new InsertionSort();
                algorithm.setSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                final int[] intArray = {3,7,4,8,9,5,9,1,3,2};
                ((InsertionSort) algorithm).setData(intArray);
                break;
            default:
                visualizer = null;
        }

        VisualizerController.setInterval(Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getString("preference_interval", "500")));
        algorithm.setStarted(false);
//        fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
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
//                    fab.setImageResource(R.drawable.ic_pause_white_24dp);
                    logFragment.clearLog();
//                    bottomBar.selectTabAtPosition(1, true);//move to log fragment
                } else {
                    if (algorithm.isPaused()) {
                        algorithm.setPaused(false);
  //                      fab.setImageResource(R.drawable.ic_pause_white_24dp);
                    } else {
                        algorithm.setPaused(true);
    //                    fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    }
                }
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


}
