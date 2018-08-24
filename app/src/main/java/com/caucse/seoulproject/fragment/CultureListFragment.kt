package com.caucse.seoulproject.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.caucse.seoulproject.R
import com.caucse.seoulproject.adapter.CultureListAdapter
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.viewmodel.ListViewModel
import com.caucse.seoulproject.viewmodel.MainViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class CultureListFragment : Fragment() {

    private val TAG = "CultureListFragment"
    private val owner = this

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var cultureData : ArrayList<CultureRow>
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var fm: FragmentManager
    private lateinit var mainViewModel : MainViewModel

    private lateinit var adapter : CultureListAdapter

    var disposable : Disposable = Disposables.disposed()
    private val viewModel by lazy { ListViewModel(activity!!.applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view : View =  inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.listview
        recyclerView.setItemViewCacheSize(20)
        recyclerView.isDrawingCacheEnabled = true

        linearLayoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.layoutManager= linearLayoutManager
        adapter = CultureListAdapter(recyclerView, fm)
        recyclerView.adapter = adapter

        mainViewModel.initData(context!!).observe(owner, Observer<ArrayList<CultureRow>> {
            livedata -> adapter.addData(livedata!!.toList())

        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState : Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !recyclerView.canScrollVertically(1)) {
                    async(UI) {
                        mainViewModel.addData(context!!).observe(owner, Observer<ArrayList<CultureRow>> {
                            livedata -> adapter.addData(livedata!!.toList())
                        })
                    }
                }
            }
        })
        return view
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
        fun newInstance(fm : FragmentManager) = CultureListFragment().apply {
            this.fm = fm
        }
    }
}
