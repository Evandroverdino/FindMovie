package com.evlj.findmovie.base.adapter.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerScrollListener : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private val visibleThreshold = 5
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager) {
            with(layoutManager) {
                val visibleItemCount = childCount
                val totalItemCount = itemCount
                val firstVisibleItemPosition = findFirstVisibleItemPosition()

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }

                if (!loading && totalItemCount - visibleItemCount <= firstVisibleItemPosition + visibleThreshold) {
                    loadMoreData()
                    loading = true
                }
            }
        }
    }

    abstract fun loadMoreData()
}