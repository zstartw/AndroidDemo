package com.betterzw.servicedemo

import android.app.IntentService
import android.content.Intent
import android.os.Messenger
import android.util.Log
import org.greenrobot.eventbus.EventBus
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.util.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


/**
 *
 * Created by zhengwu on 3/19/18.
 */
class IntentServiceDemo : IntentService("Intent Service") {

    var urlString:String = "https://www.baidu.com"


    override fun onHandleIntent(intent: Intent?) {

        Log.d("tag", "responseCode=====begin")

        var localURL:URL

        try {
            localURL = URL(urlString)

            var urlConnection: URLConnection = localURL.openConnection()

            if (urlConnection is HttpURLConnection) {

                // Casts the connection to a HTTP connection
                val localHttpURLConnection = urlConnection as HttpURLConnection

                // Sets the user agent for this request.
                localHttpURLConnection.setRequestProperty("User-Agent", USER_AGENT)

                // Gets a response code from the RSS server
                val responseCode = localHttpURLConnection.responseCode


                when(responseCode){
                    HttpURLConnection.HTTP_OK -> printResult(localHttpURLConnection.getInputStream())
                }


                EventBus.getDefault().post(MessageEvent(number = responseCode))

                Log.d(TAG, "responseCode:"+responseCode)
            }
        }finally {

        }
    }

    fun printResult(inputStream: InputStream){



        val inputAsString = inputStream.bufferedReader().use { it.readText() }  // defaults to UTF-8
        Log.d(TAG, inputAsString)


        /*inputStream.reader().forEachLine {
            Log.d(TAG, it)
        }*/


        /*val r = BufferedReader(InputStreamReader(inputStream))
        val total = StringBuilder()
        var line: String
        while ((line = r.readLine()) != null) {
            total.append(line).append('\n')
        }*/
    }

    companion object {

        val TAG:String = IntentServiceDemo::class.java.simpleName

        /**
         * A user-agent string that's sent to the HTTP site. It includes information about the device
         * and the build that the device is running.
         */
        val USER_AGENT = ("Mozilla/5.0 (Linux; U; Android "
                + android.os.Build.VERSION.RELEASE + ";"
                + Locale.getDefault().toString() + "; " + android.os.Build.DEVICE
                + "/" + android.os.Build.ID + ")")
    }
}