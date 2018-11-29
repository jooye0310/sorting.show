package com.example.yehoon.sorting_show;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_timSort, btn_dualPivotQuickSort, btn_radixSort, btn_insertionSort, btn_mergeSort, btn_quickSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_timSort = (Button) findViewById(R.id.btn_doSort);
        btn_dualPivotQuickSort = (Button) findViewById(R.id.btn_dualPivotQuickSort);
        btn_radixSort = (Button) findViewById(R.id.btn_radixSort);
        btn_insertionSort = (Button) findViewById(R.id.btn_insertionSort);
        btn_mergeSort = (Button) findViewById(R.id.btn_mergeSort);
        btn_quickSort = (Button) findViewById(R.id.btn_quickSort);

        btn_timSort.setOnClickListener(new Clicker());
        btn_dualPivotQuickSort.setOnClickListener(new Clicker());
        btn_radixSort.setOnClickListener(new Clicker());
        btn_insertionSort.setOnClickListener(new Clicker());
        btn_mergeSort.setOnClickListener(new Clicker());
        btn_quickSort.setOnClickListener(new Clicker());
    }

    private class Clicker implements View.OnClickListener {
        public void onClick(View v) {
            Bundle myData;
            switch (v.getId()){
                // Tim Sort selected
                case R.id.btn_doSort:
                    Intent timSortIntent = new Intent(MainActivity.this, TimSortController.class);
                    myData = new Bundle();
                    myData.putInt("myRequestCode", 101);
                    startActivityForResult(timSortIntent, 101);
                    break;
                // Dual Pivot Quick Sort selected
                case R.id.btn_dualPivotQuickSort:
                    Intent dualPivotQuickSortIntent = new Intent(MainActivity.this, DualPivotQuickSortController.class);
                    myData = new Bundle();
                    myData.putInt("myRequestCode", 102);
                    startActivityForResult(dualPivotQuickSortIntent, 102);
                    break;
                // Radix Sort button selected
                case R.id.btn_radixSort:
                    Intent radixSortIntent = new Intent(MainActivity.this, RadixSortController.class);
                    myData = new Bundle();
                    myData.putInt("myRequestCode", 103);
                    startActivityForResult(radixSortIntent, 103);
                    break;
                // Insertion Sort selected
                case R.id.btn_insertionSort:
                    Intent insertionSortIntent = new Intent(MainActivity.this, InsertionSortController.class);
                    myData = new Bundle();
                    myData.putInt("myRequestCode", 104);
                    startActivityForResult(insertionSortIntent, 104);
                    break;
                // Merge Sort selected
                case R.id.btn_mergeSort:
                    Intent mergeSortIntent = new Intent(MainActivity.this, MergeSortController.class);
                    myData = new Bundle();
                    myData.putInt("myRequestCode", 105);
                    startActivityForResult(mergeSortIntent, 105);
                    break;
                // Quick Sort button clicked
                case R.id.btn_quickSort:
                    Intent quickSortIntent = new Intent(MainActivity.this, QuickSortController.class);
                    myData = new Bundle();
                    myData.putInt("myRequestCode", 106);
                    startActivityForResult(quickSortIntent, 106);
                    break;
            }
        }
    }
}
