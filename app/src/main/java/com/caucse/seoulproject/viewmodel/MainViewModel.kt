package com.caucse.seoulproject.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.caucse.seoulproject.MainActivity
import com.caucse.seoulproject.adapter.CultureListAdapter
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.helper.CultureApiHelper
import com.caucse.seoulproject.helper.DatabaseHelper
import com.caucse.seoulproject.helper.table.Favorite
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.async
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {

    val TAG = "MainViewModel"

    private val cultureApiHelper by lazy { CultureApiHelper() }
    private lateinit var title : String
    private lateinit var myInfo : String

    private var concertData: MutableLiveData<ArrayList<CultureRow>> = MutableLiveData()
    private val recentData: ArrayList<CultureRow> = ArrayList()

    val favoriteData : MutableLiveData<HashMap<String, CultureRow>> = MutableLiveData()
    val favoriteKeyList = MutableLiveData<ArrayList<String>>()
    lateinit var fragmentManager: FragmentManager

    var curConcert : CultureRow? = null
    init {
        //userid = "boohyunsik"
        favoriteData.value = HashMap()
        favoriteKeyList.value = ArrayList()
    }


    fun addRecentData(row: CultureRow) {
        recentData.forEach {
            if (it.CULTCODE == row.CULTCODE) {
                return
            }
        }

        if (recentData.size > 4) {
            recentData.removeAt(4)
        }
        recentData.add(0, row)
    }

    fun getRecentDataItemCount(): Int {
        return recentData.size
    }

    fun getRecentDataItem(index: Int): CultureRow{
        return recentData[index]
    }

    fun initData(context: Context): MutableLiveData<ArrayList<CultureRow>> {
        var data = cultureApiHelper.getData(context, 0, 10).SearchConcertDetailService.row
        concertData.value = data
        Log.d(TAG, "data size = ${concertData.value?.size}")
        return concertData
    }

    fun addData(context: Context): MutableLiveData<ArrayList<CultureRow>> {
        val endIndex: Int = concertData.value!!.size
        var data = cultureApiHelper.getData(context,endIndex+1, endIndex+10).SearchConcertDetailService.row
        concertData.value!!.addAll(data)

        val ret: MutableLiveData<ArrayList<CultureRow>> = MutableLiveData<ArrayList<CultureRow>>()
        ret.value = data
        return ret
    }


    fun addFavoriteData(row: CultureRow) {
        favoriteData.value!!.put(row.CULTCODE, row)
        favoriteKeyList.value!!.add(row.CULTCODE)
    }

    fun delFavoriteData(row: CultureRow) {
        favoriteData.value!!.remove(row.CULTCODE)
        favoriteKeyList.value!!.forEach {
            if (row.CULTCODE == it) {
                favoriteKeyList.value!!.remove(it)
            }
        }
    }

    fun isFavoriteEmpty(): Boolean {
        return favoriteData.value!!.size == 0
    }

    fun printFavoriteData() {
        Log.d(TAG, "favorite hash map data size = ${favoriteData.value!!.size}")
        Log.d(TAG, "favorite key data size = ${favoriteKeyList.value!!.size}")
    }
    fun setMyInfo(myinfo: String){
        myInfo = myinfo
    }

    fun getMyInfo() : List<String>{
        myInfo = myInfo.replace("\"","")
        myInfo = myInfo.replace("{","")
        myInfo = myInfo.replace(" ","")
        val myName = myInfo.split(":|,".toRegex())
        return myName
    }

    fun setTitle(cultureTitle : String){
        title = cultureTitle
    }
    fun getTitle() : String{
        return title
    }
}