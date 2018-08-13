package com.betterzw.androiddemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var title = findViewById<TextView>(R.id.title)
        title.post {
//            title.setText("Hello")
            Log.d("MainActivity", "===="+title.width+",=="+title.height)
        }


        var start = findViewById<Button>(R.id.start);
        start.setOnClickListener(View.OnClickListener {
            startActivity(Intent(it.context, TestActivity::class.java))
        })
    }

    override fun onPause() {
        super.onPause()

        Log.d("MainActivity", "====onPause")
    }

    override fun onStop() {
        super.onStop()


        Log.d("MainActivity", "====onStop")
    }
}
