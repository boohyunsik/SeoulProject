package com.caucse.seoulproject.data

data class HotelData(val TursmStayingInfo : TursmStayingInfo) : DataFormat(){
}

class TursmStayingInfo {
    var list_total_count: Int? = null
    var RESULT: Result? = null
    lateinit var row: ArrayList<HotelRow>
}

data class HotelRow (
        val ID : String,
        val NM : String,
        val ADDR_OLD : String,
        val ADDR : String,
        val PERMISSION_DT : String,
        val STATE : String,
        val STOP_DT : String,
        val SUSPENSION_START_DT : String,
        val SUSPENSION_END_DT : String,
        val RE_OPEN_DT : String,
        val AREA : String,
        val POST : String,
        val ROOM_CNT : String,
        val BUILDING_PURPOSE : String,
        val BUILDING_AREA : String,
        val TRAVEL_INSRNC_STT_DT : String,
        val TRAVEL_INSRNC_END_DT : String,
        val CULTURE_BUSINESS_TYPE : String,
        val CULTURE_TYPE : String,
        val INSRNC_NM : String,
        val INSRNC_STT_DT : String,
        val INSRNC_END_DT : String,
        val EQUIP_SCALE : String,
        val INFO_YN : String,
        val ENG_NAME : String,
        val MEDICAL_ROOM_YN : String,
        val CAPITAL : String,
        val TRTMNT_CONTENTS : String,
        val CFR_ENVRN : String,
        val AREA_NM : String,
        val TEL : String,
        val XCODE : String,
        val YCODE : String,
        val PERMISSION_NO : String,
        val DETAIL_STAT_NM : String)