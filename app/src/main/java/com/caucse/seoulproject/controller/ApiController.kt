package com.caucse.seoulproject.controller

import android.content.Context
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.ParkingData
import com.caucse.seoulproject.helper.CultureApiHelper
import com.caucse.seoulproject.helper.ParkingApiHelper

object ApiController {

    val cultureApiHelper : CultureApiHelper = CultureApiHelper()
    val parkingApiHelper : ParkingApiHelper = ParkingApiHelper()
    var context : Context? = null

    fun getCultureData(start : Int, end : Int) : CultureData {
        return cultureApiHelper.getData(context, 1, 3)
    }

    fun getParkingData(start : Int, end : Int) : ParkingData {
        return parkingApiHelper.getData(context, 1, 3)
    }
}