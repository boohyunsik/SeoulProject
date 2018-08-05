package com.caucse.seoulproject.controller

import android.content.Context
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.helper.CultureApiHelper

object ApiController {

    fun getCultureData(context : Context, start : Int, end : Int) : CultureData {
        return CultureApiHelper.getData(context, 1, 3)
    }
}