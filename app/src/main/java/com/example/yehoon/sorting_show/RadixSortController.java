package com.example.yehoon.sorting_show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;

public class RadixSortController extends AppCompatActivity {

    TextView unsorted_list, sorted_list;
    Button btn_timSort, btn_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radixsortdemo);
        final int[] intArray = {4,7,6,8,9,5,9,1,3,2};
        DataSet ds = new DataSet(intArray, intArray.length);
        final MainFragment algoFragment = MainFragment.newInstance(VisualizerController.RADIX_SORT, ds);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, algoFragment).commit();
//        initTextViews();
//        initButtons();

        // Initialize variables for RadixSort;
//        final Integer[] array = new Integer[] {7, 8, 6, 10, 5, 4, 3, 2, 1, 9};
        final int[] array1 ={702,2,3,50,5,52,3,57,1,108};

        String unsorted = intArrayToString(array1);
//        unsorted_list.setText(unsorted);

//        btn_timSort.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // do radix sort using int[] array
//                doRadixSort(array1);
//                String sorted = intArrayToString(array1);
//                sorted_list.setText(sorted);
//            }
//        });
    }


    private int[] doRadixSort(int[] a){
        int length = a.length;
        RadixSort radixSort = new RadixSort();
        radixSort.radixsort(a, length);
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
//        sorted_list = (TextView) findViewById(R.id.sorted_list);
        unsorted_list.setText("");
//        sorted_list.setText("");
    }

    private void initButtons() {
//        btn_timSort = (Button) findViewById(R.id.btn_doSort);
//        btn_goBack = (Button) findViewById(R.id.btn_goBack);
//        btn_goBack.setOnClickListener(new ButtonListener());
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
