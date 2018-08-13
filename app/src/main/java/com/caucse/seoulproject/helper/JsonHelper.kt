package com.caucse.seoulproject.helper

import android.util.Log
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.HotelData
import com.caucse.seoulproject.data.ParkingData
import com.google.gson.Gson

// This object parses string to json, gson
object JsonHelper {
    val TAG = "JsonHelper"
    val gson = Gson()
    fun parseCultureData(str : String?) : CultureData {
        var cultureData : CultureData = gson.fromJson(str, CultureData::class.java)
        return cultureData
    }

    fun parseParkingData(str : String?) : ParkingData {
        var parkingData : ParkingData = gson.fromJson(str, ParkingData::class.java)
        return parkingData
    }

    fun parseHotelData(str : String?) : HotelData {
        var hotelData : HotelData = gson.fromJson(str, HotelData::class.java)
        return hotelData
    }
}