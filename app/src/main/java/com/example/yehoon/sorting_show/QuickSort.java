package com.example.yehoon.sorting_show;

public class QuickSort extends VisualizerController
{
    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    @Override
    public void run() {
        super.run();
    }
    int[] arr;

    private int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        addLog(pivot + " is a new pivot for array index from" + low + " to " + high);
        sleep();
        sleep();
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                highlightSwap(i, j);
                addLog(temp + " is less or equal to the pivot, so swap");
                sleep();

            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        addLog("Restore the pivot or arr[" + high + "]");
        sleep();

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sortRecur(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);
            addLog("Divide the array from " + low + " to " + high);
            highlightSwap(low, high);
            sleep();


            // Recursively sort elements before
            // partition and after partition
            sortRecur(arr, low, pi-1);
            sortRecur(arr, pi+1, high);
        }
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
            sortRecur(arr, 0, arr.length-1);
            addLog("Array has been sorted");
            completed();
        }
    }

}
/*This code is contributed by Rajat Mishra */
