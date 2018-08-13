package com.caucse.seoulproject.helper

import android.content.Context
import android.util.Log
import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.HotelData

class HotelApiHelper {
    private val TAG = "HotelApiHelper"

    init {
        Log.d(TAG, "init()")
    }

    fun getData(context : Context?, start : Int, end : Int) : HotelData {
        Log.d(TAG, "connect()")
        val builder: StringBuilder = StringBuilder().append("http://openapi.seoul.go.kr:8088")
                .append(context?.resources?.getString(R.string.key))
                .append("/json/TursmStayingInfo/")
                .append("${start}/${end}/")
        val url = builder.toString()
        var response : String? = HttpHelper().execute(url).get()
        Log.d(TAG, response)
        return JsonHelper.parseHotelData(response)
    }
}