package com.example.yehoon.sorting_show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;

public class InsertionSortController extends AppCompatActivity {

    TextView unsorted_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertionsortdemo);
        final MainFragment algoFragment = MainFragment.newInstance(VisualizerController.INSERTION_SORT);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, algoFragment).commit();


        initTextViews();
//        initButtons();

        // Initialize variables (a, c) for TimSort
        final Integer[] array = new Integer[] {3,7,4,8,9,5,9,1,3,2};
        final int[] intArray = {3,7,4,8,9,5,9,1,3,2};

        String unsorted = intArrayToString(intArray);
        unsorted_list.setText(unsorted);
    }

    // Sort the array using TimSort
    private int[] doInsertionSort(int[] a) {
        InsertionSort basicsort = new InsertionSort();
        basicsort.InsertionSort(a);
        return a;
    }

    // Converts an integer array to a string
    private String intArrayToString(int[] intArray) {
        String intArrayString = "";
        for (int i = 0; i < intArray.length; i++) {
            if (i < intArray.length - 1)
                intArrayString += intArray[i] + ", ";
            else
                intArrayString += intArray[i];
        }
        return intArrayString;
    }

    private void initTextViews() {
        unsorted_list = (TextView) findViewById(R.id.unsorted_list);
        //sorted_list = (TextView) findViewById(R.id.sorted_list);
        unsorted_list.setText("");
        //sorted_list.setText("");
    }

    private void initButtons() {
//        btn_timSort = (Button) findViewById(R.id.btn_doSort);
        //btn_goBack = (Button) findViewById(R.id.btn_goBack);
        //btn_goBack.setOnClickListener(new ButtonListener());
    }

    // Click listener for buttons
    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_goBack:
                    Intent mainIntent = getIntent();
                    Bundle myData = new Bundle();
                    setResult(Activity.RESULT_OK, mainIntent);
                    finish();
            }
        }
    }
}
