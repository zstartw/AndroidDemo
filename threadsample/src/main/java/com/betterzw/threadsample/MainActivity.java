package com.betterzw.threadsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

//        StringBuilder rowStr = new StringBuilder();
//        String test = "test";
//        Thread t5 = new MyThread4();
//        show = new String("title");

        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(25));
        /**
         * 基本线程池使用
         */
        findViewById(R.id.start_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0;i<30;i++){
                    final int finali = i;
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                Log.d("Thread", "run: "+finali);
                                Log.d("Thread-当前线程：",Thread.currentThread().getName());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    threadPoolExecutor.execute(runnable);
                }
            }
        });

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
