package com.caucse.seoulproject.helper

import android.util.Log
import com.caucse.seoulproject.data.CultureData
import com.google.gson.Gson
import okhttp3.*

object CultureApiHelper : ApiHelper() {
    private val TAG = "CultureApiHelper"
    private val key = "667a614a766268733132356b47506c7a"
    private val url = ("http://openAPI.seoul.go.kr:8088/"
            + key
            + "/xml"
            + "/SearchConcertDetailService"
            + "/1/5/")

    init {
        Log.d(TAG, "init()")
    }

    override fun connect() {
        Log.d(TAG, "connect()")
        val response : String = HttpHelper.execute(url).get()
        Log.d(TAG, response)
    }
}