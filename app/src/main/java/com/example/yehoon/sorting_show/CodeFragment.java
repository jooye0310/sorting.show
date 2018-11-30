package com.example.yehoon.sorting_show;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CodeFragment extends Fragment {

    LinearLayout codeLayout;

    public static CodeFragment newInstance(String algorithm) {
        CodeFragment fragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(VisualizerController.KEY_ALGORITHM, algorithm);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_code, container, false);

        codeLayout = (LinearLayout) rootView.findViewById(R.id.codeLayout);

//        setCode(getArguments().getString(VisualizerController.KEY_ALGORITHM));

        return rootView;
    }
//
//    public void setCode(String key) {
//        if (codeLayout != null) {
//            codeLayout.removeAllViews();
//            switch (key) {
//                case VisualizerController.INSERTION_SORT:
//                    addCodeItem(VisualizerController.CODE_INSERTION_SORT, "Insertion sort");
//                    break;
//            }
//        }
//
//    }
//
//    private void addCodeItem(String code, String title) {
//        View codeitem = LayoutInflater.from(getActivity()).inflate(R.layout.item_code_view, codeLayout, false);
//
//        CodeView codeView = (CodeView) codeitem.findViewById(R.id.code_view);
//        TextView titleText = (TextView) codeitem.findViewById(R.id.title);
//
//        titleText.setText(title);
//
//        codeView.setTheme(CodeViewTheme.GITHUB);
//        codeView.setHorizontalScrollBarEnabled(true);
//
//        codeView.setOnTouchListener(new HorizontalMoveListener());
//
//        codeView.showCode(code);
//
//        codeLayout.addView(codeitem);
//
//    }

    /**
     * handle horizontal move
     */
    class HorizontalMoveListener implements View.OnTouchListener {

        float downX = 0;
        float downY = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float dx = Math.abs(event.getX() - downX);
                    float dy = Math.abs(event.getY() - downY);

                    if(dx > dy){
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                    }

                    downX = event.getX();
                    downY = event.getY();

                    break;
            }
            return false;
        }
    }
}
