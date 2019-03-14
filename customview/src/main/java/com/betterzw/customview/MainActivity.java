package com.betterzw.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.betterzw.customview.widget.CheckView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    private int age = 1111;
    private String name="123123123";
    private enum test{zheng};
    private int[] args = {1,2};
    private String[] names = {"1", "2"};

    private static int use;
    private static String[] books = {"1", "2"};


    private ArrayList<String> phone = new ArrayList<>();
    private static ArrayList<String> booklist = new ArrayList<>();


    private ArrayList<Object> objList = new ArrayList<>();

    private boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView textView = new TextView(this);

//        final ImageView imageView = findViewById(R.id.imageView2);


       /* findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG","begin");

               *//* while (true){

                    Runtime rt = Runtime.getRuntime();
                    long maxMemory = rt.maxMemory();
                    long freeMemory = rt.freeMemory();
                    Log.v("onCreate", "maxMemory:" + Long.toString(maxMemory)+"=="+freeMemory);

                    newBitmap = Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888);
                }*//*



                 Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
//                imageView.setImageBitmap(newBitmap);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*if (newBitmap != null){
                    newBitmap.recycle();
                    newBitmap = null;
                }
*//*
                System.gc();
            }
        });*/
//        setContentView(new XfermodesView(this));


        /*Resources res = getResources();

        final PieChart pie = (PieChart) this.findViewById(R.id.Pie);
        pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
        pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
        pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
        pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
        pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
        pie.addItem("Ganymede", 3, res.getColor(R.color.slate));

        ((Button) findViewById(R.id.Reset)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pie.setCurrentItem(0);
            }
        });*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
