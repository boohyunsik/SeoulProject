package com.caucse.seoulproject

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import com.caucse.seoulproject.controller.ApiController
import com.caucse.seoulproject.data.CultureData
import com.caucse.seoulproject.data.CultureRow
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.app_bar_list.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

class ListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG = "ListActivity"
    lateinit var listView : ListView
    lateinit var cultureData : CultureData

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

        listView = findViewById(R.id.listview)

        cultureData = ApiController.getCultureData(1,1)

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

    inner class ListAdapter : BaseAdapter() {

        private val inflater : LayoutInflater = LayoutInflater.from(applicationContext)

        override fun getCount() : Int {
            return cultureData.SearchConcertDetailService.row.size
        }

        override fun getItem(position: Int): Any {
            return cultureData.SearchConcertDetailService.row.get(position)
        }

        override fun getItemId(position: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view : View
            val holder : RowHolder

            if (convertView == null) {
                view = inflater.inflate(R.layout.list_item, parent, false)
                holder = RowHolder(view)
            } else {
                view = convertView
                holder = view.tag as RowHolder
            }




            return view
        }
    }


    inner class RowHolder(row: View?) {
        public val titleImageView : ImageView = row?.findViewById(R.id.title_image) as ImageView
    }
}
