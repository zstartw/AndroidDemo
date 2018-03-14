package com.betterzw.layoutmanagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betterzw.layoutmanagerdemo.adapter.MyAdapter;
import com.betterzw.layoutmanagerdemo.adapter.MyCardAdapter;
import com.betterzw.layoutmanagerdemo.adapter.MyFlowAdapter;
import com.betterzw.layoutmanagerdemo.adapter.MyImageAdapter;
import com.betterzw.layoutmanagerdemo.layoutmanager.MyLayoutManager3;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link MyLayoutManager3}:
 * 只实现了简单的 onLayoutChildren() 和 scrollVerticallyBy() 的竖向 LayoutManager，实现了 view 的回收，可以竖向滑动
 * 页面加载后 adapter 会先调用 50 次 onCreateViewHolder() 和 onBindViewHolder()，然后调用 8 次 onCreateViewHolder() 和 13 次 onBindViewHolder()
 * 页面加载后 RecyclerView 的子 view 有 38 个
 * 在滑动中获取到的 RecyclerView 的子 view 数是 13、14 个
 */
public class Activity3 extends AppCompatActivity {
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

        setMyLayoutManager3();

        recyclerview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                MLog.d("onScrollChange : " + recyclerview.getChildCount());
            }
        });
    }

    private void setMyLayoutManager3() {
        recyclerview.setLayoutManager(new MyLayoutManager3());
        recyclerview.setAdapter(myAdapter);
    }
}
