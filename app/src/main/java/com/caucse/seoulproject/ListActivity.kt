package com.caucse.seoulproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.caucse.seoulproject.helper.CultureApiHelper
import kr.go.seoul.culturalevents.CulturalEventButtonTypeA
import kr.go.seoul.culturalevents.CulturalEventSearchTypeA

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        CultureApiHelper.connect(applicationContext, 1, 1)

    }
}
