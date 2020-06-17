package com.betterzw.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public Context mContext;

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());


      /*  recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE){


                    final MyAdapter.MyViewHolder viewHolder = (MyAdapter.MyViewHolder)
                            recyclerView.findViewHolderForAdapterPosition(1);


                    if (VideoManager.titleView == null){
                        VideoManager.titleView = new TextView(mContext);

                        VideoManager.titleView.setText("12333333333333");

                        viewHolder.videoContainer.addView(VideoManager.titleView);
                    }else {

                        TextView textView = VideoManager.titleView;

                        if (textView.getParent() != null) {
                            ((ViewGroup) (textView.getParent())).removeView(textView);
                        }


                        viewHolder.videoContainer.addView(VideoManager.titleView);
                    }
                }
            }
        });*/
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Log.d("tag", "==========="+holder.toString());

        }

        @Override
        public int getItemCount() {
            return 100;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            FrameLayout videoContainer;

            public MyViewHolder(View itemView) {
                super(itemView);

                videoContainer = itemView.findViewById(R.id.video_container);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, OtherActivity.class));
                    }
                });

            }
        }
    }
}
