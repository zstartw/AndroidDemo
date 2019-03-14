package com.betterzw.layoutmanagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;


import com.betterzw.layoutmanagerdemo.adapter.MyAdapter;
import com.betterzw.layoutmanagerdemo.adapter.MyCardAdapter;
import com.betterzw.layoutmanagerdemo.adapter.MyFlowAdapter;
import com.betterzw.layoutmanagerdemo.adapter.MyImageAdapter;
import com.betterzw.layoutmanagerdemo.layoutmanager.MyAnimHorizontalLayoutManager2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link MyAnimHorizontalLayoutManager2}:
 * 带有滑动效果的横向的 LayoutManager，修改自 {@link }
 * 页面加载后 adapter 会调用 3 次 onCreateViewHolder() 和 onBindViewHolder()
 * 页面加载后 RecyclerView 的子 view 有 2 个
 * 在滑动中获取到的 RecyclerView 的子 view 数有 3 个
 */
public class Activity8 extends AppCompatActivity {


    public static final String TAG = Activity8.class.getSimpleName();


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    MyAdapter myAdapter;
    MyFlowAdapter myFlowAdapter;
    MyImageAdapter myImageAdapter;
    MyCardAdapter myCardAdapter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        ButterKnife.bind(this);

        myAdapter = new MyAdapter();
        myFlowAdapter = new MyFlowAdapter();
        myImageAdapter = new MyImageAdapter();
        myCardAdapter = new MyCardAdapter();

        setMyAnimHorizontalLayoutManager2();

        /*recyclerview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                MLog.d("onScrollChange : " + recyclerview.getChildCount());
            }


        });*/

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Log.d(TAG, "onScrollStateChanged: "+newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.d(TAG, "onScrolled: "+dx+",==="+dy);
            }
        });

        recyclerview.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                MLog.d("onFling : " + recyclerview.getChildCount());
                return false;
            }
        });
    }

    private void setMyAnimHorizontalLayoutManager2() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


//        recyclerview.setLayoutManager(new MyAnimHorizontalLayoutManager2());
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(myImageAdapter);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerview);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
//        PagerSnapHelper snapHelper = new PagerSnapHelper();



//        snapHelper.attachToRecyclerView(recyclerview);
    }
}
