package com.caucse.seoulproject.helper

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureData

class HallApiHelper {
    private val TAG = "HallApiHelper"
    init {
        Log.d(TAG, "init()")
    }

    fun getData(context : Context?, hallName : String) : CultureData {
        Log.d(TAG, "connect()")
        val builder: StringBuilder = StringBuilder().append("http://openAPI.seoul.go.kr:8088/")
                .append(context?.resources?.getString(R.string.key))
                .append("/json/SearchCulturalFacilitiesDetailService/1/1/$hallName/")

        val url = builder.toString()
        var response : String? = HttpHelper().execute(url).get()
        Log.d(TAG, response)
        return JsonHelper.parseCultureData(response)
    }
}