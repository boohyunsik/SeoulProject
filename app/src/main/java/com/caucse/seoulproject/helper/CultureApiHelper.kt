package com.caucse.seoulproject.helper

import android.content.Context
import android.util.Log
import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureData
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import okhttp3.*

open class CultureApiHelper : ApiHelper() {
    private val TAG = "CultureApiHelper"

    init {
        Log.d(TAG, "init()")
    }

    override fun getData(context : Context?, start : Int, end : Int) : CultureData{
        Log.d(TAG, "connect()")
        val builder: StringBuilder = StringBuilder().append("http://openAPI.seoul.go.kr:8088/")
                .append(context?.resources?.getString(R.string.key))
                .append("/json/SearchConcertDetailService/")
                .append("${start}/${end}/")
        val url = builder.toString()
        var response : String? = HttpHelper().execute(url).get()
        Log.d(TAG, response)
        return JsonHelper.parseCultureData(response)
    }
}