package com.betterzw.androiddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var title = findViewById<TextView>(R.id.title)
        title.post {
//            title.setText("Hello")
            Log.d("test", "===="+title.width+",=="+title.height)
        }
    }
}
