package com.caucse.seoulproject.data

data class CultureData(val SearchConcertDetailService: SerachConcertDetailService) : DataFormat()

class SerachConcertDetailService {
    var list_total_count: Int? = null
    var RESULT: Result? = null
    lateinit var row: ArrayList<CultureRow>
}

data class Result (val CODE : String, val MESSAGE : String)

data class CultureRow (val CULTCODE : String,
                val SUBJCODE : String,
                val CODENAME : String,
                val TITLE : String,
                val STARTDATE : String,
                val END_DATE : String,
                val TIME : String,
                val PLACE : String,
                val ORG_LINK : String,
                val MAIN_IMG : String,
                val HOMEPAGE : String,
                val USE_TRGT : String,
                val USE_FEE : String,
                val SPONSOR : String,
                val INQUIRY : String,
                val SUPPORT : String,
                val ETC_DESC : String,
                val AGELIMIT : String,
                val IS_FREE : String,
                val TICKET : String,
                val PROGRAM : String,
                val PLAYER : String,
                val CONTENTS : String,
                val GCODE : String)



