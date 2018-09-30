package com.caucse.seoulproject.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.caucse.seoulproject.MainActivity
import com.caucse.seoulproject.R
import com.caucse.seoulproject.adapter.RecentCardListAdapter
import com.caucse.seoulproject.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_my_info.view.*


class MyInfoFragment : Fragment() {
    val TAG = "MyInfoFragment"
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recentCardView: RecyclerView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recentRecyclerView: RecyclerView
    private lateinit var adapter: RecentCardListAdapter
    private lateinit var myInfo : List<String>

    private lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        myInfo  = mainViewModel.getMyInfo()
      }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_my_info, container, false)
        recentRecyclerView = view.myinfo_recyclerView
        linearLayoutManager = object: LinearLayoutManager(activity?.applicationContext) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recentRecyclerView.layoutManager= linearLayoutManager
        adapter = RecentCardListAdapter(recentRecyclerView, mainViewModel)
        recentRecyclerView.adapter = adapter


        val recentTextView = view.recent_textView
        if (mainViewModel.getRecentDataItemCount() == 0) {
            recentTextView.setText("최근 본 문화정보가 없습니다.")
        } else {
            recentTextView.setText("최근 본 문화정보 (${mainViewModel.getRecentDataItemCount()}개)")
        }

        val imgURL = myInfo.get(5)+":"+myInfo.get(6)
        Glide.with(this).load(imgURL).into(view.profile_img)
        view.profile_name.setText(myInfo.get(14))
        view.profile_email.setText(myInfo.get(12))
        if(myInfo.get(10) == "M"){
            view.profile_sex.setText("남성")
        }else{
            view.profile_sex.setText("여성")
        }
        view.profile_age.setText("${myInfo.get(8).substringBefore("-")}대")


        return view
    }


    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        Log.d(TAG, "queue size = ${mainViewModel.getRecentDataItemCount()}")
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyInfoFragment()
    }
}
