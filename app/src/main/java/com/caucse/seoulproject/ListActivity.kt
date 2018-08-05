package com.caucse.seoulproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.caucse.seoulproject.controller.ApiController
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.helper.CultureApiHelper
import kr.go.seoul.culturalevents.CulturalEventButtonTypeA
import kr.go.seoul.culturalevents.CulturalEventSearchTypeA

class ListActivity : AppCompatActivity() {

    var cultureData : CultureData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }

    override fun onResume() {
        super.onResume()
        cultureData = ApiController.getCultureData(applicationContext, 1, 3)
    }
}
