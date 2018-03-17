package com.betterzw.threadsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* HashMap<String, String> test = new HashMap<>();
        test.put("1", "rock");
        test.put("2", "zw");
        test.put("1", "111");

        for (String key : test.keySet()){
            Log.d(TAG, "name:"+key);
            Log.d(TAG, "value:"+test.get(key));
        }

        Log.d(TAG, "onCreate: ==="+new String("1").hashCode());
        Log.d(TAG, "onCreate: ==="+new String("12").hashCode());
        Log.d(TAG, "onCreate: ==="+new String("13").hashCode());
        Log.d(TAG, "onCreate: ==="+(new String("1111111111").hashCode() & 1));

        Log.d(TAG, "onCreate==========: "+new Object().hashCode());*/
    }
}
