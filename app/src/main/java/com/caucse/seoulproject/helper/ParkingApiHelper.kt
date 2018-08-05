package com.caucse.seoulproject.helper

import android.content.Context
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.DataFormat
import com.caucse.seoulproject.data.ParkingData

object ParkingApiHelper : ApiHelper() {


    override fun getData(context: Context, start: Int, end: Int) : DataFormat {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return ParkingData(1)
    }
}