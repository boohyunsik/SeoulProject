package com.caucse.seoulproject

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ListFragment
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
    lateinit var loginInfo : String

    lateinit var listfragment : CultureListFragment
    lateinit var favoriteFragment: FavoriteFragment
    lateinit var myInfoFragment : MyInfoFragment

    lateinit var mainViewModel : MainViewModel

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        if ((item.itemId == R.id.navigation_home && active == listfragment) ||
                (item.itemId == R.id.navigation_dashboard && active == favoriteFragment) ||
                (item.itemId == R.id.navigation_notifications && active == myInfoFragment)) {
            return@OnNavigationItemSelectedListener false
        }
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
        when (item.itemId) {
            R.id.navigation_home -> {
                active = listfragment
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
        navigation.selectedItemId = item.itemId
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent = getIntent()
        loginInfo = intent.getStringExtra("LoginInfo")
        var bundle = Bundle()
        bundle.putString("logininfo",loginInfo)


        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.setMyInfo(loginInfo)
        fragmentManager = supportFragmentManager
        mainViewModel.fragmentManager = fragmentManager

        listfragment = CultureListFragment.newInstance()
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

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG, "in back : " + fragmentManager.backStackEntryCount)
        if (fragmentManager.backStackEntryCount == 0) {
            Log.d(TAG, "back key pressed()")
            navigation.selectedItemId = R.id.navigation_home
        }
    }
}

