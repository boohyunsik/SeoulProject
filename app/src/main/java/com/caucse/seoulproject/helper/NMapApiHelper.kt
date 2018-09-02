package com.caucse.seoulproject.helper

import android.content.Context
import android.util.Log
import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureData
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object NMapApiHelper {
    val TAG = "NMapApiHelper"

    fun getData(context: Context?, name: String) : Observable<String?> {

        return Observable.create(ObservableOnSubscribe {
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("https://openapi.naver.com/v1/map/geocode?encoding=utf-8&coordType=latlng&query=$name")
                    .addHeader("X-Naver-Client-Id", "ILMvRUaQNzCAYsTvxb59")
                    .addHeader("X-Naver-Client-Secret", "tNuAP0CtD5")
                    .build()
            val response = client.newCall(request).execute()
            it.onNext(response.body()?.string())
            it.onComplete()
        })
    }
}