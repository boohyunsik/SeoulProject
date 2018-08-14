package com.caucse.seoulproject


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ListFragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.caucse.seoulproject.controller.ApiController
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.fragment.CultureListFragment
import com.caucse.seoulproject.fragment.FavoriteFragment
import com.caucse.seoulproject.fragment.MyInfoFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.experimental.async
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var frameLayout : FrameLayout
    lateinit var active : Fragment
    lateinit var fragmentManager : FragmentManager

    lateinit var listfragment : CultureListFragment
    lateinit var favoriteFragment: FavoriteFragment
    lateinit var myInfoFragment : MyInfoFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fragmentManager.beginTransaction().hide(active).show(listfragment).commit()
                active = listfragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                fragmentManager.beginTransaction().hide(active).show(favoriteFragment).commit()
                active = favoriteFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fragmentManager.beginTransaction().hide(active).show(myInfoFragment).commit()
                active = myInfoFragment
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
        frameLayout = frame_layout
        fragmentManager = supportFragmentManager

        listfragment = CultureListFragment.newInstance()
        favoriteFragment = FavoriteFragment.newInstance()
        myInfoFragment = MyInfoFragment.newInstance()

        fragmentManager.beginTransaction().add(R.id.frame_layout, myInfoFragment, "3").hide(myInfoFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame_layout, favoriteFragment, "2").hide(favoriteFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame_layout, listfragment, "1").commit()

        fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)

        active = listfragment
    }

    private fun loadFragByTag(tag : String) {
        var frag = fragmentManager.findFragmentByTag(tag)
        if (frag == null) {
            when (tag) {

            }
        }
    }
}

