package com.betterzw.materialdesigndemo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.TextView


/**
 * snackbar 用例
 */
class MainActivity : AppCompatActivity() {

    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var btnSimpleSnackbar: Button
    lateinit var btnActionCallback: Button
    lateinit var btnCustomView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coordinatorLayout = findViewById(R.id.constraint)
        toolbar = findViewById(R.id.toolbar)


        setSupportActionBar(toolbar)


        btnSimpleSnackbar = findViewById(R.id.btnSimpleSnackbar)
        btnActionCallback = findViewById(R.id.btnActionCallback)
        btnCustomView = findViewById(R.id.btnCustomSnackbar)

        btnSimpleSnackbar.setOnClickListener(View.OnClickListener {
            val snackbar = Snackbar
                    .make(coordinatorLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG)
            snackbar.show()
        })

        btnActionCallback.setOnClickListener {
            val snackbar = Snackbar
                    .make(coordinatorLayout, "Message is deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        val snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT)
                        snackbar1.show()
                    }

            snackbar.show()
        }

        btnCustomView.setOnClickListener {
            val snackbar = Snackbar
                    .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY") { }

            // Changing message text color
            snackbar.setActionTextColor(Color.RED)

            // Changing action button text color
            val sbView = snackbar.view
            val textView = sbView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.YELLOW)

            snackbar.show()
        }
    }
}
