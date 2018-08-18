package com.caucse.seoulproject.fragment

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView

import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureRow
import com.nhn.android.maps.nmapdata.t
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_info.view.*
import kotlinx.android.synthetic.main.nav_header_list.*

class InfoFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var cultureData : CultureRow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View= inflater.inflate(R.layout.fragment_info, container, false)
        var readView:TextView = view.findViewById(R.id.readMoreTextView)
        var titleView:TextView = view.findViewById(R.id.titleView)
        var titleContent =  "타이틀입니다"
        var infoContent = """testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtesting
testingtestingtestingtestingtestingtestingtestingtesting
testingtestingtestingtestingtestingtestingtestingtestingtesting
testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtesting
testingtestingtestingtestingtestingtestingtestingtestingtesting
testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtesting
testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtesting이부분 안나오나"""
      //  Log.d("error",R.drawable.ic_test_info.toString())
        titleView.setText(titleContent)
        readView.setText(infoContent)

        var imageView: ImageView = view.findViewById(R.id.infoImageView)
        imageView.setImageResource(R.drawable.ic_test_info)

        return view
    }
    // TODO: Insert Naver Map
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        fun newInstance(data : CultureRow) = InfoFragment().apply {
            this.cultureData = data
        }
    }
}
