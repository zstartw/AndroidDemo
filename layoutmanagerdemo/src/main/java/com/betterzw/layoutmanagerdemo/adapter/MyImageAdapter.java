package com.betterzw.layoutmanagerdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.betterzw.layoutmanagerdemo.MLog;
import com.betterzw.layoutmanagerdemo.R;

import butterknife.ButterKnife;

public class MyImageAdapter extends RecyclerView.Adapter<MyImageAdapter.MyViewHolder> {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MLog.d();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_image, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MLog.d("onBindViewHolder " + position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
