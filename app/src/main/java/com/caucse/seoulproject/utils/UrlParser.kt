package com.caucse.seoulproject.utils

object UrlParser {
    val TAG = "UrlParser"

    fun parseUrl(url: String) : String {
        var ret: String = url.substring(0, url.length-3)
        val format: String = url.substring(url.length-3).toUpperCase()
        return ret + format
    }

}