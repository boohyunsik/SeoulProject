package com.caucse.seoulproject.data

data class ParkingData(val GetParkInfo : GetParkInfo): DataFormat()

class GetParkInfo {
    var list_total_count: Int? = null
    var RESULT: Result? = null
    lateinit var row : ArrayList<ParkingRow>
}

data class ParkingRow (
        val PARKING_CODE : String? = null,
        val PARKING_NAME : String? = null,
        val ADDR : String? = null,
        val PARKING_TYPE : String? = null,
        val PARKING_TYPE_NM : String? = null,
        val OPERATION_RULE : String? = null,
        val OPERATION_RULE_NM : String? = null,
        val TEL : String? = null,
        val QUE_STATUS : String? = null,
        val QUE_STATUS_NM : String? = null,
        val CAPACITY : String? = null,
        val CUR_PARKING : String? = null,
        val CUR_PARKING_TIME : String? = null,
        val PAY_YN : String? = null,
        val PAY_NM : String? = null,
        val NIGHT_FREE_OPEN : String? = null,
        val NIGHT_FREE_OPEN_NM : String? = null,
        val WEEKDAY_BEGIN_TIME : String? = null,
        val WEEKDAY_END_TIME : String? = null,
        val WEEKEND_BEGIN_TIME : String? = null,
        val WEEKEND_END_TIME : String? = null,
        val HOLIDAY_BEGIN_TIME : String? = null,
        val HOLIDAY_END_TIME : String? = null,
        val SYNC_TIME : String? = null,
        val SATURDAY_PAY_YN : String? = null,
        val SATURDAY_PAY_NM : String? = null,
        val HOLIDAY_PAY_YN : String? = null,
        val HOLIDAY_PAY_NM : String? = null,
        val FULLTIME_MONTHLY : String? = null,
        val GRP_PARKNM : String? = null,
        val RATES : String? = null,
        val TIME_RATE : String? = null,
        val ADD_RATES : String? = null,
        val ADD_TIME_RATE : String? = null,
        val BUS_RATES : String? = null,
        val BUS_TIME_RATE : String? = null,
        val BUS_ADD_TIME_RATE : String? = null,
        val BUS_ADD_RATES : String? = null,
        val DAY_MAXIMUM : String? = null,
        val LAT : Float? = null,
        val LNG : Float? = null,
        val ASSIGN_CODE : String? = null,
        val ASSIGN_CODE_NM : String? = null
)
