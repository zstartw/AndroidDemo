package com.betterzw.androiddemo

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by zhengwu on 6/11/18.
 */
class TestActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.weight_bak)

        Log.d("TestActivity", "====onCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.d("TestActivity", "====onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("TestActivity", "====onResume")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        Log.d("TestActivity", "====onAttachedToWindow")

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)



        Log.d("TestActivity", "====onWindowFocusChanged:"+hasFocus)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        Log.d("TestActivity", "====onPostCreate:")
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        Log.d("TestActivity", "====onPostCreate22:")
    }
}