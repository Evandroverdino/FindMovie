package com.evlj.findmovie.list.listener

import android.nfc.tech.MifareUltralight
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class RecyclerScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager) {
            with(layoutManager) {
                val visibleItemCount = childCount
                val totalItemCount = itemCount
                val firstVisibleItemPosition = findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= MifareUltralight.PAGE_SIZE
                ) {
                    loadMoreData()
                }
            }
        }
    }

    abstract fun loadMoreData()

}