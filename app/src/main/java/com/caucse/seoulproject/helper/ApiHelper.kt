package com.caucse.seoulproject.helper

import android.content.Context
import com.caucse.seoulproject.data.DataFormat

open abstract class ApiHelper {
    open abstract fun getData(context: Context, start: Int, end: Int) : DataFormat
}