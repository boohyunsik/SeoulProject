package com.caucse.seoulproject.data

class CultureData constructor(STARTDATE : String, END_DATE : String){
    var STARTDATE : String? = null
    var END_DATE : String? = null
    override fun toString() : String {
        return "$STARTDATE ~ $END_DATE"
    }
}

