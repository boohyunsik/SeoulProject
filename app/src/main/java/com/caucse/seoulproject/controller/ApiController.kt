package com.caucse.seoulproject.controller

import android.content.Context
import android.graphics.Bitmap
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.ParkingData
import com.caucse.seoulproject.helper.CultureApiHelper
import com.caucse.seoulproject.helper.ImageDownloadHelper
import com.caucse.seoulproject.helper.ParkingApiHelper

object ApiController {
    val cultureApiHelper : CultureApiHelper = CultureApiHelper()
    val parkingApiHelper : ParkingApiHelper = ParkingApiHelper()
    val imageDownloadHelper : ImageDownloadHelper = ImageDownloadHelper()
    lateinit var context : Context

    fun getCultureData(start : Int, end : Int) : CultureData {
        return cultureApiHelper.getData(context, start, end)
    }

    fun getParkingData(start : Int, end : Int) : ParkingData {
        return parkingApiHelper.getData(context, start, end)
    }

    fun getBitmap(url : String) : Bitmap {
        return imageDownloadHelper.download(url)
    }
}