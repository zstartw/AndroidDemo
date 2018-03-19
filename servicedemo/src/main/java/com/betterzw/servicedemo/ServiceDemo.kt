package com.betterzw.servicedemo

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * Created by zhengwu on 3/19/18.
 */
class ServiceDemo : Service() {

//    val TAG: String? = ServiceDemo::class.simpleName


    val myLocalBinder: MyBinder = MyBinder()


    class MyBinder : Binder() {

        fun getServie(): ServiceDemo {
            val a: ServiceDemo = ServiceDemo()
            return a
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "onBind:"+Thread.currentThread().id)
        return myLocalBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun startService(service: Intent?): ComponentName {
        Log.d(TAG, "startService")
        return super.startService(service)
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d(TAG, "stopService")
        return super.stopService(name)
    }

    override fun bindService(service: Intent?, conn: ServiceConnection?, flags: Int): Boolean {
        Log.d(TAG, "bindService")
        return super.bindService(service, conn, flags)
    }

    override fun unbindService(conn: ServiceConnection?) {
        Log.d(TAG, "unbindService")
        super.unbindService(conn)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")


        Thread.sleep(20000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }


    companion object {
        private val TAG = ServiceDemo::class.java.simpleName
    }
}