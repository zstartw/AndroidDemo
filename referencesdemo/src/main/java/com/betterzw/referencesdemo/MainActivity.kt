package com.betterzw.referencesdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var book = Book()
        var tmp = Book()

        var bookReference = WeakReference<Book>(book)
        var softReference = SoftReference<Book>(tmp)


        var r = WeakReference("I here")
        var sr = SoftReference("========")

        Log.d(TAG, "before gc:r="+r.get()+","+sr.get())

        System.gc()

        Thread.sleep(100)

        Log.d(TAG, "after gc:r="+r.get()+","+sr.get())

        findViewById<Button>(R.id.button).setOnClickListener {

            if (bookReference.get() != null) {
                Log.d(TAG, "weak object not gc")
            } else {
                Log.d(TAG, "weak object gc")
            }

            if (softReference.get() != null) {
                Log.d(TAG, "soft object not gc")
            } else {
                Log.d(TAG, "soft object not gc")
            }
        }

    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}
