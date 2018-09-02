package com.caucse.seoulproject.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
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

class MainViewModel : ViewModel() {

    val TAG = "MainViewModel"

    private val cultureApiHelper by lazy { CultureApiHelper() }
    private lateinit var userid: String
    private lateinit var username: String

    private var concertData : MutableLiveData<ArrayList<CultureRow>> = MutableLiveData<ArrayList<CultureRow>>()

    var curConcert : CultureRow? = null
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

    fun getIsFavorited(context : Context, cultureCode : String) : Single<Favorite> {
        Log.d(TAG, "getIsFavorited()")
        return DatabaseHelper
                .getInstance(context)!!
                .getFavoriteDao()
                .load(userid, cultureCode)
    }

    fun setFavorite(context: Context, data: CultureRow) {
        setFavorite(context, data.CULTCODE)
    }

    fun setFavorite(context: Context, cultureCode: String) {
        val favorite: Favorite = Favorite()
        favorite.cultureCode = cultureCode
        favorite.userId = userid
        Log.d(TAG, "insert to db : cultcode=${cultureCode}, userid=${userid}")
        async {
            DatabaseHelper
                    .getInstance(context)!!
                    .getFavoriteDao()
                    .insert(favorite)
        }
    }

    fun delFavorite(context: Context, cultureCode: String) {
        async {
            DatabaseHelper
                    .getInstance(context)!!
                    .getFavoriteDao()
                    .delete(userid, cultureCode)
        }
    }


}