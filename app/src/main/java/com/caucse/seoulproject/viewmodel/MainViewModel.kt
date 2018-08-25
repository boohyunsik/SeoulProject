package com.caucse.seoulproject.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.helper.CultureApiHelper
import com.caucse.seoulproject.helper.DatabaseHelper
import com.caucse.seoulproject.helper.table.Favorite

class MainViewModel : ViewModel() {

    val TAG = "MainViewModel"

    private val cultureApiHelper by lazy { CultureApiHelper() }
    private lateinit var userid: String
    private lateinit var username: String

    private var concertData : MutableLiveData<ArrayList<CultureRow>> = MutableLiveData<ArrayList<CultureRow>>()

    init {
        userid = "boohyunsik"
    }

    fun isInit() : Boolean {
        return concertData.value != null
    }

    fun initData(context: Context): MutableLiveData<ArrayList<CultureRow>> {
        var data = cultureApiHelper.getData(context, 0, 3).SearchConcertDetailService.row
        concertData.value = data
        Log.d(TAG, "data size = ${concertData.value?.size}")
        return concertData
    }

    fun addData(context: Context): MutableLiveData<ArrayList<CultureRow>> {
        val endIndex: Int = concertData.value!!.size
        var data = cultureApiHelper.getData(context,endIndex+1, endIndex+3).SearchConcertDetailService.row
        concertData.value!!.addAll(data)

        val ret: MutableLiveData<ArrayList<CultureRow>> = MutableLiveData<ArrayList<CultureRow>>()
        ret.value = data
        return ret
    }

    fun getIsFavorited(context : Context, cultureCode : String) : Boolean {
        var data: LiveData<Favorite> = DatabaseHelper
                .getInstance(context)!!
                .getFavoriteDao()
                .load(userid, cultureCode)

        if (data.value == null) return false
        return true
    }
}