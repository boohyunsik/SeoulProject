package com.caucse.seoulproject.viewmodel

import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.helper.CultureApiHelper
import io.reactivex.Observable
import java.util.concurrent.ThreadPoolExecutor

class ListViewModel (val context : Context) {
    val TAG = "ListViewModel"
    var cultureData : ArrayList<CultureRow> = ArrayList()
    val cultureApiHelper by lazy { CultureApiHelper() }

    fun initData() : Observable<List<CultureRow>> {
        Log.d(TAG, "initData()")
        cultureData.clear()
        return getData(cultureData.size, cultureData.size + 3)
    }

    fun requestAdditionalData() : Observable<List<CultureRow>> {
        Log.d(TAG, "requestAdditionalData()")
        return getData(cultureData.size + 1, cultureData.size + 3)
    }

    fun getData(start: Int, end: Int) : Observable<List<CultureRow>> {
        Log.d(TAG ,"getData($start, $end)")
        return Observable.create{
            subscriber ->
            var data = cultureApiHelper.getData(context, start, end).SearchConcertDetailService.row
            cultureData.addAll(data)
            subscriber.onNext(data)
        }
    }
}