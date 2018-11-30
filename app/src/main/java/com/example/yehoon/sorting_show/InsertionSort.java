package com.example.yehoon.sorting_show;

public class InsertionSort extends VisualizerController{
    /*Function to sort array using insertion sort*/
    @Override
    public void run() {
        super.run();
    }
    int[] arr;

    void sort()
    {
        int n = arr.length;
        for (int j = 1; j < n; j++) {
            int key = arr[j];
            int i = j-1;
            while ( (i > -1) && ( arr [i] > key ) ) {
                arr [i+1] = arr [i];
                highlightSwap(i, i + 1);
                addLog("Swapping " + arr[i] + " and " + arr[i + 1]);
                i--;
            }
            sleep();
            arr[i+1] = key;
        }
        addLog("Array has been sorted");
        completed();
    }

    void InsertionSort(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                highlightSwap(i, i + 1);
                addLog("Swapping " + arr[i] + " and " + arr[i + 1]);
                j = j-1;
            }
            sleep();
            arr[j+1] = key;
        }

        addLog("Array has been sorted");
        completed();
    }

    @Override
    public void onDataRecieved(Object data) {
        super.onDataRecieved(data);
        this.arr = (int[]) data;
    }

    @Override
    public void onMessageReceived(String message) {
        super.onMessageReceived(message);
        if (message.equals(VisualizerController.COMMAND_START_ALGORITHM)) {
            startExecution();
            sort();
        }
    }


}
