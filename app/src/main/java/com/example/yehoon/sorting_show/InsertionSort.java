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
            sort();
        }
    }


}
