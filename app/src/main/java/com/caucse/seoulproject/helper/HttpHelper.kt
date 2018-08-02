package com.caucse.seoulproject.helper

import android.os.AsyncTask
import android.util.Log
import okhttp3.*
import java.util.concurrent.TimeUnit

class HttpHelper : AsyncTask<String, Unit, String>() {

    private val TAG = "HttpHelper"
    private val TIMEOUT = 10L
    private var okHttpClient: OkHttpClient

    init {
        Log.d(TAG, "init()")
        okHttpClient = OkHttpClient().newBuilder().apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    // This will return string that is converted by body
    override fun doInBackground(vararg params: String?): String? {
        Log.d(TAG, "doInBackground()")
        Log.d(TAG, "url : ${params[0]}")
        val url = params[0]
        var client : OkHttpClient = OkHttpClient()
        var body : RequestBody = FormBody.Builder().build()
        var request : Request = Request.Builder().apply{
            url(url)
        }.build()

        val response: Response = client.newCall(request).execute()
        return response.body()?.string()
    }
}