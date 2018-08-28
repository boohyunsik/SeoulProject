package com.caucse.seoulproject

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.fragment.CultureListFragment
import com.caucse.seoulproject.fragment.FavoriteFragment
import com.caucse.seoulproject.fragment.MyInfoFragment
import com.caucse.seoulproject.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var frameLayout : FrameLayout
    lateinit var active : Fragment
    lateinit var fragmentManager : FragmentManager

    lateinit var listfragment : CultureListFragment
    lateinit var favoriteFragment: FavoriteFragment
    lateinit var myInfoFragment : MyInfoFragment

    lateinit var mainViewModel : MainViewModel

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
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
        frameLayout = frame_layout
        fragmentManager = supportFragmentManager

        listfragment = CultureListFragment.newInstance(fragmentManager)
        favoriteFragment = FavoriteFragment.newInstance()
        myInfoFragment = MyInfoFragment.newInstance()

        setFragmentManager()
    }

    private fun setFragmentManager() {
        fragmentManager.beginTransaction().add(R.id.frame_layout, myInfoFragment).hide(myInfoFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame_layout, favoriteFragment).hide(favoriteFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame_layout, listfragment).commit()
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        active = listfragment
    }
}

