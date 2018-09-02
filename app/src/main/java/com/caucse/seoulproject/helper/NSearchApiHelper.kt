package com.caucse.seoulproject.helper

import android.content.Context
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

object NSearchApiHelper {
    val TAG = "NSearchApiHelper"

    fun getData(context: Context?, name: String) : Observable<JSONObject?> {

        return Observable.create(ObservableOnSubscribe {
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("https://openapi.naver.com/v1/search/local?encoding=utf-8&coordType=latlng&query=$name")
                    .addHeader("X-Naver-Client-Id", "ILMvRUaQNzCAYsTvxb59")
                    .addHeader("X-Naver-Client-Secret", "tNuAP0CtD5")
                    .build()
            val response = client.newCall(request).execute()
            val json = JSONObject(response.body()?.string())
            it.onNext(json.getJSONArray("items").getJSONObject(0))
            it.onComplete()
        })
    }
}