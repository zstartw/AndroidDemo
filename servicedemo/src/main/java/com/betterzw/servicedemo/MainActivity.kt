package com.betterzw.servicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
class MainActivity : AppCompatActivity() {

    var isBound: Boolean = false
    var myBoundService: ServiceDemo? = null


    /**
     * @JvmField
     * internal
     */

    @BindView(R.id.button1)
    @JvmField var button1:Button ?= null

    @BindView(R.id.button2)
    @JvmField var button2:Button ? = null

    @OnClick(R.id.button1, R.id.button2, R.id.button3, R.id.button4)
    internal fun onClick(view: View) {

        Log.d(TAG, "onClick====")

        when(view.id){
            R.id.button1 -> bindService()
            R.id.button2 -> unbindService()
            R.id.button3 -> startService()
            R.id.button4 -> stopService()
        }
    }


    var myServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected")
            isBound = false
        }

        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {

            Log.d(TAG, "onServiceConnected")

            var myLocalBinder: ServiceDemo.MyBinder = iBinder as ServiceDemo.MyBinder
            myBoundService = myLocalBinder.getServie()
            isBound = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_service)

        ButterKnife.bind(this)


       /* findViewById<Button>(R.id.button1).setOnClickListener {
            bindService()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            unbindService()
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            startService()
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            stopService()
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            startIntentService()
        }*/

     /*   var intent = Intent(this, ServiceDemo::class.java)
        bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE)


        Thread(Runnable {

            var intent = Intent(this, ServiceDemo::class.java)
            bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE)

            Log.d(TAG, "run:"+Thread.currentThread().id)
        }).start()*/
    }

    fun startIntentService(){
        var intent = Intent(this, IntentServiceDemo::class.java)
        startService(intent)
    }

    fun startService(){
        var intent = Intent(this, ServiceDemo::class.java)
        startService(intent)
    }

    fun stopService(){
        var intent = Intent(this, ServiceDemo::class.java)
        stopService(intent)
    }

    fun bindService(){
        var intent = Intent(this, ServiceDemo::class.java)
        bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE)
    }

    fun unbindService(){
        if (isBound){
            unbindService(myServiceConnection)
            isBound = false
        }
    }

    companion object {
        private var TAG = MainActivity::class.java.simpleName
    }
}
