package com.caucse.seoulproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.caucse.seoulproject.controller.ApiController
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.ParkingData
import com.caucse.seoulproject.helper.CultureApiHelper
import kr.go.seoul.culturalevents.CulturalEventButtonTypeA
import kr.go.seoul.culturalevents.CulturalEventSearchTypeA

class ListActivity : AppCompatActivity() {
    val TAG = "ListActivity"
    var cultureData : CultureData? = null
    var parkingData : ParkingData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }

    override fun onResume() {
        super.onResume()
        cultureData = ApiController.getCultureData(1, 3)
        parkingData = ApiController.getParkingData(1, 2)
        //
        // Log.d(TAG, cultureData?.SearchConcertDetailService?.row?.get(0)?.CONTENTS)
    }
}
