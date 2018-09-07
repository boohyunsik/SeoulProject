package com.caucse.seoulproject.fragment.`interface`

import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v7.widget.RecyclerView
import com.caucse.seoulproject.adapter.CultureListAdapter
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.fragment.CultureListFragment
import com.caucse.seoulproject.viewmodel.MainViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class IScrollListener(val context: Context, val mainViewModel: MainViewModel
                      , val adapter: CultureListAdapter, val owner: CultureListFragment)
    : RecyclerView.OnScrollListener(){

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE && !recyclerView.canScrollVertically(1)) {
            async(UI) {
                mainViewModel.addData(context!!).observe(owner, Observer<ArrayList<CultureRow>> {
                    livedata -> adapter.addData(livedata!!.toList())
                })
            }
        }
    }
}