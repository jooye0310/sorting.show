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

public class DualPivotQuickSortController extends AppCompatActivity {

    TextView sorted_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dualpivotquicksortdemo);
        int[] input = new int[30];

        initTextViews();

        for (int i = 0; i < input.length;i++){
            input[i] = (int)(Math.random() * 15 + 1);
        }

        final int[] intArray = input.clone();

        Arrays.sort(input);
        String s = Arrays.toString(input);
        sorted_list.setText(s);

        DataSet ds = new DataSet(intArray, 0);
        final MainFragment algoFragment = MainFragment.newInstance(VisualizerController.DUALPIVOTQUICKSORT, ds);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, algoFragment).commit();

    }


    private void initTextViews() {
        sorted_list = (TextView) findViewById(R.id.sorted_list);
        sorted_list.setText("");
    }

}
