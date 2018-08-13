package com.betterzw.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by zhengwu on 8/7/18.
 */
public class OtherActivity  extends AppCompatActivity {

    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        frameLayout = findViewById(R.id.other_container);


        TextView textView = VideoManager.titleView;

        if (textView.getParent() != null) {
            ((ViewGroup) (textView.getParent())).removeView(textView);
        }

        frameLayout.addView(textView);

    }
}
