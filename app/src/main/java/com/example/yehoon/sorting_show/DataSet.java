package com.example.yehoon.sorting_show;

import java.io.Serializable;

public class DataSet implements Serializable {

    public int[] arr;
    public int n;

    public DataSet(int[] arr, int n){
        this.arr = arr;
        this.n = n;
    }
}
