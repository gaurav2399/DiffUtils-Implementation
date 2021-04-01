package com.gaurav.diffutilimplementaton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gaurav.diffutilimplementaton.Util.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button btn, btn_update;
    RecyclerView recyclerView;

    List<String> dataSource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btn = findViewById(R.id.btn);
        btn_update = findViewById(R.id.btn_update);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initData();

        MyAdapter myAdapter = new MyAdapter(dataSource);
        recyclerView.setAdapter(myAdapter);

        btn.setOnClickListener(view -> {
            // inserting new data
            Log.e("insert btn click","yes");
            List<String> insertList = new ArrayList<>(dataSource);
            for(int i=0;i<3;i++)
                insertList.add(UUID.randomUUID().toString());
            Log.e("list size ","" + insertList.size());
            myAdapter.insertData(insertList);
            recyclerView.smoothScrollToPosition(myAdapter.getItemCount()-1);
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("update btn click","yes");
                List<String> updateList = new ArrayList<>();
                for(int i=0;i<3;i++)
                    updateList.add(UUID.randomUUID().toString());
                Log.e("update list size","" + updateList.size());
                myAdapter.updateData(updateList);
            }
        });
    }

    private void initData() {
        for(int i=0;i<3;i++)
            dataSource.add(UUID.randomUUID().toString());
    }
}