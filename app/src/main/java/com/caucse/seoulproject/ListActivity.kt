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
import android.view.accessibility.AccessibilityEvent
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
import kotlinx.android.synthetic.main.nav_header_list.view.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import java.lang.Exception
// Createed by hyunsik boo.
class ListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG = "ListActivity"
    private var isLoading = false
    private val visibleThreshold = 1

    private var lastIndex = 10

    private lateinit var recyclerView : RecyclerView
    private lateinit var cultureData : ArrayList<CultureRow>
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
        lastIndex = 10
        cultureData = ApiController.getCultureData(1,lastIndex)

        // TODO : 이런식으로 intent에다가 key로 묶어서 로그인 정보 전달
        val userName = intent.extras.getString("userName")
        val userEmail = intent.extras.getString("userEmail")

        // Settings navigation header view
        val navigationView = this.nav_view
        val headerView = navigationView.getHeaderView(0)
        headerView.nav_user_name.setText(userName)
        headerView.nav_user_email.setText(userEmail)

        recyclerView = this.listview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager= linearLayoutManager
        val adapter : Adapter = Adapter(applicationContext, recyclerView)
        recyclerView.adapter = adapter
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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

    inner class Adapter(context : Context, recyclerView: RecyclerView) : RecyclerView.Adapter<RowHolder>() {

        private val TAG = "ListAdapter"
        private val inflater : LayoutInflater = LayoutInflater.from(context)
        lateinit var onLoadMoreListener: OnLoadMoreListener
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

        open fun setListener(listener : OnLoadMoreListener) {
            this.onLoadMoreListener = listener
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
