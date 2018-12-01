package com.example.yehoon.sorting_show;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FullCode extends AppCompatActivity {
    LogAdapter adapter;
    RecyclerView recyclerView;
    View emptyView;
    public List<String> fullLogList;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullcode);
        recyclerView = findViewById(R.id.logrecyclerview);
        emptyView = findViewById(R.id.empty_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        fullLogList = getIntent().getExtras().getStringArrayList("code");

        adapter = new LogAdapter(fullLogList);

        recyclerView.setAdapter(adapter);
        adapter.show();

    }

    public void addLog(final String log) {
        emptyView.setVisibility(View.GONE);
        fullLogList.add(log);
        adapter.addLog(log);
    }

    public void clearLog() {
        if (adapter != null)
            adapter.clearLog();
        if (emptyView != null)
            emptyView.setVisibility(View.VISIBLE);
    }

}
