package com.caucse.seoulproject.controller

import android.content.Context
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.helper.CultureApiHelper

object ApiController {

    val cultureApiHelper : CultureApiHelper = CultureApiHelper()
    var context : Context? = null

    fun getCultureData(start : Int, end : Int) : CultureData {
        return cultureApiHelper.getData(context, 1, 3)
    }
}