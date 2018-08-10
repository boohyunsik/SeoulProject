package com.caucse.seoulproject

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import com.caucse.seoulproject.controller.ApiController
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.CultureRow
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.app_bar_list.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import java.lang.Exception

class ListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG = "ListActivity"
    private lateinit var recyclerView : RecyclerView
    private lateinit var cultureData : CultureData
    private lateinit var adapter : Adapter
    private lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
        cultureData = ApiController.getCultureData(1,10)

        recyclerView = this.listview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager= linearLayoutManager
        recyclerView.adapter = Adapter(applicationContext)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    inner class Adapter(context : Context) : RecyclerView.Adapter<RowHolder>() {

        private val TAG = "ListAdapter"
        private val inflater : LayoutInflater = LayoutInflater.from(context)


        override fun getItemCount() : Int {
            Log.d(TAG, "getItemCount() " + cultureData.SearchConcertDetailService.row.size)
            return cultureData.SearchConcertDetailService.row.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
            Log.d(TAG, "onCreateViewHolder()")
            return RowHolder(inflater.inflate(R.layout.list_item, parent, false))
        }

        override fun onBindViewHolder(holder: RowHolder, position: Int) {
            val url : String = cultureData.SearchConcertDetailService.row.get(position).MAIN_IMG.toLowerCase()
            Log.d(TAG, "onBindViewHolder() -> $url")
            Picasso.get().load(url).into(holder.titleImageView, callback)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//
//            val rowView = inflater.inflate(R.layout.list_item, parent, false)
//            val imageView : ImageView = rowView.findViewById(R.id.title_image)
//            val url : String = cultureData.SearchConcertDetailService.row.get(position).MAIN_IMG.toLowerCase()
//            Log.d(TAG, "Download => ${url}")
//            Picasso.get().load(url).into(imageView, callback)
//            return rowView
//        }

        val callback: Callback = object: Callback {
            override fun onSuccess() {
                Log.d(TAG, "picasso onSuccess()")
            }

            override fun onError(e: Exception?) {
                Log.d(TAG, "picasso onFailed() -> ${e?.message}")
            }
        }
    }
    class RowHolder (row: View) : RecyclerView.ViewHolder(row) {
        var titleImageView = row.title_image
    }
}
