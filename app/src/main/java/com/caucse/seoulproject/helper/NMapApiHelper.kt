package com.caucse.seoulproject.helper

import android.content.Context
import android.util.Log
import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureData
import okhttp3.OkHttpClient
import okhttp3.Request

class NMapApiHelper {
    val TAG = "NMapApiHelper"

    fun getData(context : Context?, name: String) : String? {

        val client: OkHttpClient = OkHttpClient()
        val response = client.newCall(
                Request.Builder().
                        url("https://openapi.naver.com/v1/map/geocode?query=$name")
                        .addHeader("X-Naver-Client-Id", context!!.getString(R.string.naver_client_key))
                        .addHeader("X-Naver-Client-Secret", context!!.getString(R.string.naver_secret_key))
                        .build()
        ).execute()

        Log.d(TAG, response.body()?.string())
        return response.body()?.string()
    }
}