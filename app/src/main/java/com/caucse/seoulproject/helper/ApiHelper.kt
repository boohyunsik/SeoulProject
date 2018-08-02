package com.caucse.seoulproject.helper

import android.content.Context

open abstract class ApiHelper {
    open abstract fun connect(context: Context, start: Int, end: Int)
}