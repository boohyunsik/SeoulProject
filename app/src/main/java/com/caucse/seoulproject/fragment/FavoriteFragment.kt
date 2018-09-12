package com.caucse.seoulproject.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.caucse.seoulproject.R
import com.caucse.seoulproject.adapter.FavoriteListAdapter
import com.caucse.seoulproject.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_my_info.view.*

class FavoriteFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private val TAG = "FavoriteFragment"
    private lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        val linearLayoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView = view.favorite_recyclerView
        recyclerView.layoutManager = linearLayoutManager
        adapter = FavoriteListAdapter(mainViewModel, recyclerView)
        recyclerView.adapter = adapter

        val textView = view.favorite_textView
        if (mainViewModel.isFavoriteEmpty()) {

            val favoriteContainer = view.favorite_container
            favoriteContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            textView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            recyclerView.layoutParams.height = 0
            textView.text = "즐겨찾기 한 문화정보가 없습니다."
        } else {
            textView.layoutParams.height = 0
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
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
        fun newInstance() = FavoriteFragment()
    }
}
