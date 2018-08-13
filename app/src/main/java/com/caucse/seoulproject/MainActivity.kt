package com.caucse.seoulproject

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caucse.seoulproject.controller.ApiController
import com.caucse.seoulproject.data.CultureRow
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.experimental.async
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private var lastIndex = 0
    private lateinit var recyclerView : RecyclerView
    private lateinit var cultureData : ArrayList<CultureRow>
    private lateinit var linearLayoutManager : LinearLayoutManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
        lastIndex = 10
        cultureData = ApiController.getCultureData(1,lastIndex)

        recyclerView = this.listview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager= linearLayoutManager
        val adapter : Adapter = Adapter(applicationContext, recyclerView)
        recyclerView.adapter = adapter
    }

    inner class Adapter(context : Context, recyclerView: RecyclerView) : RecyclerView.Adapter<RowHolder>() {

        private val TAG = "ListAdapter"
        private val inflater : LayoutInflater = LayoutInflater.from(context)
        lateinit var URL : String

        init {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState : Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && !recyclerView.canScrollVertically(1)) {
                        async {
                            Log.d(TAG, "need load data")
                            val additionalData : ArrayList<CultureRow> =
                                    ApiController.getCultureData(cultureData.size + 1, cultureData.size + 3)
                            cultureData.addAll(additionalData)
                            notifyDataSetChanged()
                        }
                    }
                }
            })
        }

        override fun getItemCount() : Int {
            return cultureData.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
            return RowHolder(inflater.inflate(R.layout.list_item, parent, false))
        }

        override fun onBindViewHolder(holder: RowHolder, position: Int) {

            val data : CultureRow = cultureData.get(position)
            val url : String = data.MAIN_IMG.toLowerCase()
            val title : String = data.TITLE
            Log.d(TAG, "onBindViewHolder() -> $url")
            URL = url
            try {
                Picasso.get().load(url).into(holder.titleImageView, callback)
                holder.titleTitleView.setText(title)
            } catch (e : Exception) {
                Log.e(TAG, e.message)
            }
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        val callback: Callback = object: Callback {
            override fun onSuccess() {
                //Log.d(TAG, "picasso onSuccess()")
            }

            override fun onError(e: Exception?) {
                Log.d(TAG, "picasso onFailed() -> ${URL},  ${e?.message}")
            }
        }
    }
    class RowHolder (row: View) : RecyclerView.ViewHolder(row) {
        var titleImageView = row.title_image
        var titleTitleView = row.title_title
    }
}
