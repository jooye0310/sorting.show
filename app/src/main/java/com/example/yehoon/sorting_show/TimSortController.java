package com.example.yehoon.sorting_show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;

public class TimSortController extends AppCompatActivity {

    TextView unsorted_list, sorted_list;
    Button btn_timSort, btn_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timsortdemo);

        initTextViews();
        initButtons();

        // Initialize variables (a, c) for TimSort
        final Integer[] array = new Integer[] {7, 8, 6, 10, 5, 4, 3, 2, 1, 9};
        final Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                if (integer < t1)
                    return -1;
                else if (integer > t1)
                    return 1;
                else
                    return 0;
            }
        };

        String unsorted = intArrayToString(array);
        unsorted_list.setText(unsorted);

        btn_timSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use TimSort to sort the integer array
                doTimSort(array, c);
                String sorted = intArrayToString(array);
                sorted_list.setText(sorted);
            }
        });
    }

    // Sort the array using TimSort
    private Integer[] doTimSort(Integer[] a, Comparator<Integer> c) {
        TimSort timSort = new TimSort(a, c);
        timSort.sort(a, c);
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
        btn_timSort = (Button) findViewById(R.id.btn_doSort);
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
