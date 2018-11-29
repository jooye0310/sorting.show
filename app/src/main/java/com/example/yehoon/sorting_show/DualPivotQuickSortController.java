package com.example.yehoon.sorting_show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;

public class DualPivotQuickSortController extends AppCompatActivity {

    TextView unsorted_list, sorted_list;
    Button btn_doSort, btn_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dualpivotquicksortdemo);

        initTextViews();
        initButtons();

        // Initialize variables for MergeSort
        final Integer[] array = new Integer[] {7, 8, 6, 10, 5, 4, 3, 2, 1, 9};

        String unsorted = intArrayToString(array);
        unsorted_list.setText(unsorted);

        btn_doSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use DualPivotSort to sort the integer array
                doSort(array);
                String sorted = intArrayToString(array);
                sorted_list.setText(sorted);
            }
        });
    }

    // Sort the array using DualPivotQuickSort
    private Integer[] doSort(Integer[] a) {
        DualPivotQuickSort dpSort = new DualPivotQuickSort();
        dpSort.sort(a);
        return a;
    }

    // Converts an integer array to a string
    private String intArrayToString(Integer[] intArray) {
        String intArrayString = "";
        for (int i = 0; i < intArray.length; i++) {
            if (i < intArray.length - 1)
                intArrayString += intArray[i].toString() + ", ";
            else
                intArrayString += intArray[i].toString();
        }
        return intArrayString;
    }

    private void initTextViews() {
        unsorted_list = (TextView) findViewById(R.id.unsorted_list);
        sorted_list = (TextView) findViewById(R.id.sorted_list);
        unsorted_list.setText("");
        sorted_list.setText("");
    }

    private void initButtons() {
        btn_doSort = (Button) findViewById(R.id.btn_doSort);
        btn_goBack = (Button) findViewById(R.id.btn_goBack);
        btn_goBack.setOnClickListener(new ButtonListener());
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
