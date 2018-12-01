package com.example.yehoon.sorting_show;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ItemHolder> {
    public List<String> logList;

    public LogAdapter(List<String> list) {
        this.logList = list;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_log_item, null);

        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        itemHolder.logText.setText(logList.get(i));
    }

    @Override
    public int getItemCount() {
        return (null != logList ? logList.size() : 0);
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        TextView logText;

        public ItemHolder(View view) {
            super(view);
            logText = (TextView) view.findViewById(R.id.text);
        }

    }

    public void addLog(String log) {
        if(logList.size() > 2) clearLog();
        logList.add(log);
        notifyDataSetChanged();
    }
    public void show(){
        notifyDataSetChanged();
    }

    public void clearLog() {
        logList.clear();
        notifyDataSetChanged();
    }


}

