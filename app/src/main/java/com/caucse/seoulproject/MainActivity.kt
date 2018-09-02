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
import android.widget.Toast
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
    var backKeyPressedTime: Long = 0
    var toast: Toast? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }

        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_layout, favoriteFragment, "favorite")
                        .commit()
                active = favoriteFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_layout, myInfoFragment, "myinfo")
                        .commit()
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

        fragmentManager = supportFragmentManager
        listfragment = CultureListFragment.newInstance(fragmentManager)
        favoriteFragment = FavoriteFragment.newInstance()
        myInfoFragment = MyInfoFragment.newInstance()

        setFragmentManager()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
        frameLayout = frame_layout
    }

    private fun setFragmentManager() {
        fragmentManager.addOnBackStackChangedListener {
            Log.d(TAG, "back stack change " + fragmentManager.backStackEntryCount)
        }
        fragmentManager.beginTransaction().add(R.id.frame_layout, listfragment, "list").commit()
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        active = listfragment
    }
}