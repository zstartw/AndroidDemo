package com.betterzw.threadsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();



    String show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ExecutorService service = Executors.newFixedThreadPool(2);
       /* ExecutorService service = Executors.newCachedThreadPool();

        Thread t1 = new MyThread1();
        Thread t2 = new MyThread2();
        Thread t3 = new MyThread3();
        Thread t4 = new MyThread4();
        Thread t5 = new MyThread4();


        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.execute(t4);
        service.execute(t5);*/

        StringBuilder rowStr = new StringBuilder();

        String test = "test";

        Thread t5 = new MyThread4();

        show = new String("title");

    }

    class MyThread1 extends Thread {
        @Override
        public void run() {
            Log.d(TAG, "run: 1111");
            Log.d(TAG, "run: "+Thread.currentThread().getName());
        }
    }

    class MyThread2 extends Thread {
        @Override
        public void run() {
            Log.d(TAG, "run: 2222");
            Log.d(TAG, "run: "+Thread.currentThread().getName());
        }
    }

    class MyThread3 extends Thread {
        @Override
        public void run() {
            Log.d(TAG, "run: 333");
            Log.d(TAG, "run: "+Thread.currentThread().getName());
        }
    }

    class MyThread4 extends Thread {
        @Override
        public void run() {
            Log.d(TAG, "run: 444");
            Log.d(TAG, "run: "+Thread.currentThread().getName());
        }
    }
}
