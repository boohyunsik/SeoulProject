package com.caucse.seoulproject.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.caucse.seoulproject.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InfoFragmentMap.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InfoFragmentMap.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InfoFragmentMap : Fragment() {
    val TAG = this::class.java.simpleName
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.fragment_info_fragment_map, container, false)
    }
}
