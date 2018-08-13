package com.caucse.seoulproject.controller

import android.content.Context
import android.graphics.Bitmap
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.data.HotelData
import com.caucse.seoulproject.data.ParkingData
import com.caucse.seoulproject.helper.CultureApiHelper
import com.caucse.seoulproject.helper.HotelApiHelper
import com.caucse.seoulproject.helper.ImageDownloadHelper
import com.caucse.seoulproject.helper.ParkingApiHelper

object ApiController {
    val cultureApiHelper : CultureApiHelper = CultureApiHelper()
    val parkingApiHelper : ParkingApiHelper = ParkingApiHelper()
    val hotelApiHelper : HotelApiHelper = HotelApiHelper()
    lateinit var context : Context

    fun getCultureData(start : Int, end : Int) : ArrayList<CultureRow> {
        return cultureApiHelper.getData(context, start, end).SearchConcertDetailService.row
    }

    fun getParkingData(start : Int, end : Int) : ParkingData {
        return parkingApiHelper.getData(context, start, end)
    }

    fun getHotelData(start : Int, end : Int) : HotelData {
        return hotelApiHelper.getData(context, start, end)
    }
}