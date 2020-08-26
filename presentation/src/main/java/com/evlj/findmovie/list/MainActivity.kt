package com.evlj.findmovie.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evlj.findmovie.R
import com.evlj.findmovie.base.activity.BaseActivity
import com.evlj.findmovie.base.adapter.AnyRvAdapter
import com.evlj.findmovie.base.adapter.AnyRvItemController
import com.evlj.findmovie.detail.MovieDetailActivity
import com.evlj.findmovie.model.PMovie
import com.evlj.findmovie.shared.extensions.loadImage
import com.evlj.findmovie.shared.extensions.makeVisibleIf
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_popular_movie.view.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainContract.View {

    private val presenter by inject<MainContract.Presenter>()

    private val moviesAdapter: AnyRvAdapter by lazy {
        AnyRvAdapter(
            mapOf(PMovie::class to PopularMovieItemController),
            emptyList()
        ) { _, item ->
            presenter.onMovieClicked((item as PMovie).id)
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        setupView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun setupView() {
        setupAdapter()
        setupRecyclerView(recyclerViewMovies)
    }

    private fun setupAdapter() = presenter.loadPopularMovies()

    private fun setupRecyclerView(recyclerView: RecyclerView) = with(recyclerView) {
        layoutManager = setupLayoutManager()
        setHasFixedSize(true)
        adapter = moviesAdapter
        addOnScrollListener(presenter.getScrollListener())
    }

    private fun setupLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun populateAdapter(results: List<PMovie>) {
        with(moviesAdapter) {
            add(results)
            notifyDataSetChanged()
        }
    }

    override fun navigateToMovieDetail(movieId: Int) =
        startActivity(MovieDetailActivity.createIntent(this, movieId))

    override fun showProgressBar() = setupVisibility(true)

    override fun hideProgressBar() = setupVisibility(false)

    private fun setupVisibility(isLoadingMovies: Boolean) {
        progressLoadingMovies.makeVisibleIf(isLoadingMovies)
    }
}

object PopularMovieItemController : AnyRvItemController<PMovie>() {

    override fun createView(parent: ViewGroup): View =
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_popular_movie, parent, false)

    override fun bind(
        rootView: View,
        item: PMovie,
        onTouchEvent: (AnyRvAdapter.TouchEvent, PMovie) -> Boolean
    ) {
        with(rootView) {
            with(item) {
                poster.loadImage(posterPath)
                name.text = title
                sinopse.text = overview
            }
        }
    }
}

