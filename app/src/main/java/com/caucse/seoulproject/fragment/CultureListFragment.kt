package com.caucse.seoulproject.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.caucse.seoulproject.R
import com.caucse.seoulproject.adapter.CultureListAdapter
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.fragment.`interface`.IScrollListener
import com.caucse.seoulproject.viewmodel.MainViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class CultureListFragment : Fragment() {

    private val TAG = "CultureListFragment"
    private val KEY_RECYCLER_STATE = "recycler_state"
    private val owner = this

    private var thisView: View? = null

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var cultureData : ArrayList<CultureRow>
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var fm: FragmentManager
    private lateinit var mainViewModel : MainViewModel

    private var bundle: Bundle? = null

    private lateinit var adapter : CultureListAdapter

    var disposable : Disposable = Disposables.disposed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
        if (bundle != null) {
            var state: Parcelable = bundle!!.getParcelable(KEY_RECYCLER_STATE)
            recyclerView.layoutManager.onRestoreInstanceState(state)
        }
    }

    override fun onPause() {
        super.onPause()
        bundle = Bundle()
        var state: Parcelable = recyclerView.layoutManager.onSaveInstanceState()
        bundle!!.putParcelable(KEY_RECYCLER_STATE, state)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")

        if (thisView == null) {
            thisView = inflater.inflate(R.layout.fragment_list, container, false)
            recyclerView = thisView!!.listview

            linearLayoutManager = LinearLayoutManager(activity?.applicationContext)
            recyclerView.layoutManager= linearLayoutManager

            adapter = CultureListAdapter(recyclerView, this, mainViewModel, context!!)
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

            mainViewModel.initData(context!!).observe(owner, Observer<ArrayList<CultureRow>> {
                livedata -> adapter.initData(livedata!!.toList())
            })

            recyclerView.addOnScrollListener(IScrollListener(context!!, mainViewModel, adapter, owner))
        }

        return thisView
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        disposable.dispose()
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    companion object {
        @JvmStatic
        fun newInstance() = CultureListFragment()
    }
}
