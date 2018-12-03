package com.example.yehoon.sorting_show;

import java.util.Arrays;

public class DualPivotQuickSort extends VisualizerController{

    @Override
    public void run(){super.run();}
    int[] arr;

    private class Pivots{
        public int lp;
        public int rp;
    }

    private void swap(int[] arr, int low, int high)
    {
        addLog("Swap " + arr[low] + " and " + arr[high]);
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
        highlightSwap(low, high);
        sleep();

    }

    private void DualPivotQuickSortr(int[] arr, int low, int high)
    {
        if (low < high) {
            // lp means left pivot, and rp means right pivot.
            Pivots ps = new Pivots();
            partition(arr, low, high, ps);
            DualPivotQuickSortr(arr, low, ps.lp - 1);
            DualPivotQuickSortr(arr, ps.lp + 1, ps.rp - 1);
            DualPivotQuickSortr(arr, ps.rp + 1, high);
        }
    }

    private void partition(int[] arr, int low, int high, Pivots ps)
    {
        addLog("Current partition is from " +low + " to " + high);


        if (arr[low] > arr[high])
            swap(arr, low, high);
        // p is the left pivot, and q is the right pivot.
        int j = low + 1;
        int g = high - 1;
        int k = low + 1;
        int p = arr[low];
        addLog("Left pivot is " + arr[low] + " in index " + low);
        highlightTrace(low);
        sleep();
        int q = arr[high];

        addLog("Right pivot is " + arr[high] + " in index " + high);
        highlightDestination(high);
        sleep();


        while (k <= g) {

            // if elements are less than the left pivot
            if (arr[k] < p) {
                swap(arr, k, j);
                j++;
            }

            // if elements are greater than or equal
            // to the right pivot
            else if (arr[k] >= q) {
                while (arr[g] > q && k < g)
                    g--;
                swap(arr, k, g);
                g--;
                if (arr[k] < p) {
                    swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // bring pivots to their appropriate positions.
        addLog("Restore the pivots");
        swap(arr, low, j);
        swap(arr, high, g);

        // returning the indeces of the pivots.
        ps.lp = j;
        // from a function.

        ps.rp = g;
    }


    @Override
    public void onDataRecieved(Object data) {
        super.onDataRecieved(data);
        this.arr = ((DataSet) data).arr;

    }

    @Override
    public void onMessageReceived(String message) {
        super.onMessageReceived(message);
        if (message.equals(VisualizerController.COMMAND_START_ALGORITHM)) {
            startExecution();
            logArray("Original array - " ,arr);
            DualPivotQuickSortr(arr, 0, arr.length-1);
            addLog("Array has been sorted");
            completed();
        }
    }

}
