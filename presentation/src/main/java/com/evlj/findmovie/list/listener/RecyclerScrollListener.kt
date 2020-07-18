package com.evlj.findmovie.list.listener

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class RecyclerScrollListener : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private val visibleThreshold = 5
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        with(recyclerView) {
            layoutManager?.let { layoutManager ->
                (layoutManager as? LinearLayoutManager)
                    ?.findFirstVisibleItemPosition()
                    ?.let {
                        if (loading) {
                            if (layoutManager.itemCount > previousTotal) {
                                loading = false
                                previousTotal = layoutManager.itemCount
                            }
                        }

                        if (!loading &&
                            (layoutManager.itemCount - childCount <= it + visibleThreshold)
                        ) {
                            loadMoreData()
                            loading = true
                        }
                    }
            }
        }
    }

    abstract fun loadMoreData()

}