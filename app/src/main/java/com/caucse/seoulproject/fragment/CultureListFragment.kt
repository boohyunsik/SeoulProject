package com.caucse.seoulproject.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caucse.seoulproject.MainActivity

import com.caucse.seoulproject.R
import com.caucse.seoulproject.controller.ApiController
import com.caucse.seoulproject.data.CultureRow
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.experimental.async
import java.lang.Exception

class CultureListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var cultureData : ArrayList<CultureRow>
    private lateinit var linearLayoutManager : LinearLayoutManager
    private var lastIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view : View =  inflater.inflate(R.layout.fragment_list, container, false)
        lastIndex = 10
        cultureData = ApiController.getCultureData(1,lastIndex)

        recyclerView = view.listview
        recyclerView.setItemViewCacheSize(20)
        recyclerView.isDrawingCacheEnabled = true
        linearLayoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.layoutManager= linearLayoutManager
        val adapter : Adapter = Adapter(activity?.applicationContext!!, recyclerView)
        recyclerView.adapter = adapter
        return view
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        val TAG : String = CultureListFragment::class.java.simpleName
        fun newInstance() = CultureListFragment()
    }
    inner class Adapter(context : Context, recyclerView: RecyclerView) : RecyclerView.Adapter<RowHolder>() {

        private val TAG = "ListAdapter"
        private val inflater : LayoutInflater = LayoutInflater.from(context)
        lateinit var URL : String

        init {
            //Picasso.get().setIndicatorsEnabled(true)
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
                Picasso.get()
                        .load(url)
                        .fit()
                        .into(holder.titleImageView, callback)
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
