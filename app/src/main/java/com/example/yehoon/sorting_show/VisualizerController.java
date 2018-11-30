package com.example.yehoon.sorting_show;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.concurrent.atomic.AtomicBoolean;

public class VisualizerController extends HandlerThread implements DataHandler {

    public static final String KEY_ALGORITHM = "key_algorithm";
    public static final String COMMAND_START_ALGORITHM = "start";
    public static final String BUBBLE_SORT = "bubble_sort";
    public static final String INSERTION_SORT = "insertion_sort";
    public static final String SELECTION_SORT = "selection_sort";
    public static final String QUICKSORT = "quicksort";
    public static final String N_QUEENS = "n_queens";
    public static final String RADIX_SORT = "radix_sort";

    public LogFragment logFragment;
    public Activity activity;
    public SortingVisualizer visualizer;
//    public AlgoCompletionListener completionListener;

    private boolean started;

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    private Handler workerHandler;

    private static int INTERVAL = 500;

    public VisualizerController() {
        super("");
    }

    public void sleep() {
        sleepFor(INTERVAL);
    }

    public void sleepFor(long time) {
        try {
            sleep(time);
            if (isPaused())
                pauseExecution();
            else resumeExecution();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void startExecution() {
        started = true;
    }

    public void setPaused(boolean b) {
        paused.set(b);
        if (!b) {
            synchronized (getPauseLock()) {
                getPauseLock().notify();
            }
        }
    }

    private void pauseExecution() {
        if (paused.get()) {
            synchronized (getPauseLock()) {
                if (paused.get()) {
                    try {
                        getPauseLock().wait();
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }

    private void resumeExecution() {
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    Object getPauseLock() {
        return pauseLock;
    }


    public boolean isPaused() {
        return paused.get();
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void addLog(final String log) {
        if (logFragment != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    logFragment.addLog(log);
                }
            });
        }
    }

    public void clearLog() {
        if (logFragment!=null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    logFragment.clearLog();
                }
            });
        }
    }

    public void logArray(String message, final int[] array) {
        String arrayString = "";
        for (int i : array) {
            arrayString = arrayString.concat(" " + String.valueOf(i) + " ");
        }
        addLog(message + "[ "+arrayString +" ] total items - "+ array.length);
    }
/*
    public void setCompletionListener(AlgoCompletionListener completionListener) {
        this.completionListener = completionListener;
    }
*/
    public void prepareHandler(final DataHandler dataHandler) {
        workerHandler = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.obj instanceof String) {
                    dataHandler.onMessageReceived((String) msg.obj);
                } else {
                    dataHandler.onDataRecieved(msg.obj);
                }
                return true;
            }
        });

    }

    public void sendData(Object data) {
        workerHandler.obtainMessage(1, data).sendToTarget();
    }

    public void sendMessage(String message) {
        workerHandler.obtainMessage(1, message).sendToTarget();
    }

    public void completed() {
        started = false;
 /*       if (completionListener != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    completionListener.onAlgoCompleted();
                }
            });
        }*/
    }

    public static void setInterval(int interval) {
        INTERVAL = interval;
    }
    public void setData(final int[] array) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(array);
            }
        });
        start();
        prepareHandler(this);
        sendData(array);
    }

    public void highlightSwap(final int one, final int two) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightSwap(one, two);
            }
        });
    }

    public void highlightTrace(final int position) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightTrace(position);
            }
        });
    }

    @Override
    public void onDataRecieved(Object data) {

    }

    @Override
    public void onMessageReceived(String message) {

    }

    public void setSort(SortingVisualizer visualizer, Activity activity, LogFragment logFragment){
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

}
