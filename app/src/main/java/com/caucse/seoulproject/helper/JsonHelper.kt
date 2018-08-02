package com.caucse.seoulproject.helper

import android.util.Log
import com.caucse.seoulproject.data.CultureData
import com.google.gson.Gson

// This object parses string to json, gson
object JsonHelper {
    val TAG = "JsonHelper"
    val gson = Gson()
    fun parseCultureData(str : String) : CultureData {
        Log.d(TAG, "Original string = " + str)
        var cultureData : CultureData = gson.fromJson(str, CultureData::class.java)
        Log.d(TAG, "Parse result = ${cultureData.SearchConcertDetailService.row.toString()}")
        return cultureData
    }
}