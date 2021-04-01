package com.gaurav.diffutilimplementaton.Util;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gaurav.diffutilimplementaton.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<String> dataSource;
    public final String TAG = MyAdapter.class.getSimpleName();

    public MyAdapter(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    public void insertData(List<String> insertList){
        // This function will add new data to list.
        Log.e("adapter insert data","yes");

        Log.e(TAG,"data source size " + dataSource.size() + " insert list size " + insertList.size());

        // data source old list and insertList updated List
        MyDiffUtilCallBack myDiffUtilCallBack = new MyDiffUtilCallBack(dataSource, insertList);

        // calculateDiff to identify actual changes
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(myDiffUtilCallBack);

        Log.e(TAG,"before addAll data source size " + dataSource.size());
        Log.e(TAG,"insert list size is " + insertList.size());
        dataSource.clear();
        dataSource.addAll(insertList);
        Log.e(TAG,"after addAll data source size " + dataSource.size());

        // dispatch updatedList
        diffResult.dispatchUpdatesTo(this);
    }

    public void updateData(List<String> insertList){
        // This function will update data.
        Log.e("adapter update data","yes");

        MyDiffUtilCallBack myDiffUtilCallBack = new MyDiffUtilCallBack(dataSource, insertList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(myDiffUtilCallBack);

        dataSource.clear();
        dataSource.addAll(insertList);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
