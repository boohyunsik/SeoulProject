package com.caucse.seoulproject.helper

import android.content.Context
import android.util.Log
import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.DataFormat
import com.caucse.seoulproject.data.ParkingData

class ParkingApiHelper : ApiHelper() {
    private val TAG = "ParkingApiHelper"

    init {
        Log.d(TAG, "init()")
    }

    override fun getData(context: Context?, start: Int, end: Int) : ParkingData {
        Log.d(TAG, "connect()")
        val builder : StringBuilder = StringBuilder().append("http://openapi.seoul.go.kr:8088/")
                .append(context?.resources?.getString(R.string.key))
                .append("/json/GetParkInfo/")
                .append("${start}/${end}/")
        val url = builder.toString()
        val response : String? = HttpHelper().execute(url).get()
        Log.d(TAG, response)
        return JsonHelper.parseParkingData(response)
    }
}