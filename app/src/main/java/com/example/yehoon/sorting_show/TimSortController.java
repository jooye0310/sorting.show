package com.example.yehoon.sorting_show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;

public class TimSortController extends AppCompatActivity {

    TextView sorted_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timsortdemo);

        initTextViews();

        //final int[] input = {31, 25, 14, 83, 84, 20, 33, 18, 23, 70, 80, 82, 64, 22, 98, 89, 1, 44};
        int[] input = new int[30];
        for (int i = 0; i < input.length;i++){
            input[i] = (int)(Math.random() * 15 + 1);
        }
        final int[] intArray = input.clone();
        Arrays.sort(input);
        String s = Arrays.toString(input);
        sorted_list.setText(s);

        DataSet ds = new DataSet(intArray, 0);
        final MainFragment algoFragment = MainFragment.newInstance(VisualizerController.TIM_SORT, ds);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, algoFragment).commit();
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
        sorted_list = (TextView) findViewById(R.id.sorted_list);
        sorted_list.setText("");
    }
}
