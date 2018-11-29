package com.example.yehoon.sorting_show;

public class DualPivotQuickSort {

    private class Pivots{
        public int lp;
        public int rp;
    }

    public void sort(Integer[] arr){
        DualPivotQuickSortr(arr, 0, arr.length-1);
    }

    private void swap(Integer[] arr, int low, int high)
    {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    private void DualPivotQuickSortr(Integer[] arr, int low, int high)
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

    private void partition(Integer[] arr, int low, int high, Pivots ps)
    {
        if (arr[low] > arr[high])
            swap(arr, low, high);
        // p is the left pivot, and q is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1, p = arr[low], q = arr[high];
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
        swap(arr, low, j);
        swap(arr, high, g);

        // returning the indeces of the pivots.
        ps.lp = j;
        // from a function.

        ps.rp = g;
    }
}
