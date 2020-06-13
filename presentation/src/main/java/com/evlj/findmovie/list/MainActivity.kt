package com.evlj.findmovie.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evlj.findmovie.R
import com.evlj.findmovie.base.BaseActivity
import com.evlj.findmovie.base.adapter.AnyRvAdapter
import com.evlj.findmovie.base.adapter.AnyRvItemController
import com.evlj.findmovie.detail.MovieDetailActivity
import com.evlj.findmovie.model.Movie
import com.evlj.findmovie.shared.Constants
import com.evlj.findmovie.shared.extensions.loadImage
import com.evlj.findmovie.shared.extensions.makeVisibleIf
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_popular_movie.view.*

class MainActivity : BaseActivity(), MainContract {

    private val presenter: MainPresenter by lazy {
        val presenter = MainPresenter()
        presenter.attachView(this)
        presenter
    }

    private val moviesAdapter: AnyRvAdapter by lazy {
        AnyRvAdapter(
            mapOf(Movie::class to PopularMovieItemController),
            emptyList()
        ) { _, item ->
            presenter.onClickMovie((item as Movie).id)
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        setupAdapter()
        setupRecyclerView(recyclerViewMovies)
    }

    private fun setupAdapter() =
        presenter.loadPopularMovies(
            apiKey = Constants.API_KEY,
            language = Constants.API_LANGUAGE,
            sortBy = Constants.API_SORT_BY,
            includeAdult = false,
            includeVideo = false,
            page = Constants.API_PAGE_RESULT
        )

    private fun setupRecyclerView(recyclerView: RecyclerView) = with(recyclerView) {
        layoutManager = setupLayoutManager()
        setHasFixedSize(true)
        adapter = moviesAdapter
        addOnScrollListener(presenter.getScrollListener())
    }

    private fun setupLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun populateAdapter(results: List<Movie>) {
        moviesAdapter.add(results)
        moviesAdapter.notifyDataSetChanged()
    }

    override fun loadMorePopularMovies(pageResult: Int) =
        presenter.loadPopularMovies(
            apiKey = Constants.API_KEY,
            language = Constants.API_LANGUAGE,
            sortBy = Constants.API_SORT_BY,
            includeAdult = false,
            includeVideo = false,
            page = pageResult
        )

    override fun navigateToMovieDetail(movieId: Int) =
        startActivity(MovieDetailActivity.createIntent(this, movieId))

    override fun showProgressBar() = setupVisibility(true)

    override fun hideProgressBar() = setupVisibility(false)

    private fun setupVisibility(isLoadingMovies: Boolean) {
        progressLoadingMovies.makeVisibleIf(isLoadingMovies)
    }
}

object PopularMovieItemController : AnyRvItemController<Movie>() {

    override fun createView(parent: ViewGroup): View =
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_popular_movie, parent, false)

    override fun bind(
        rootView: View,
        item: Movie,
        onTouchEvent: (AnyRvAdapter.TouchEvent, Movie) -> Boolean
    ) {
        with(rootView) {
            with(item) {
                poster.loadImage(Constants.API_POSTER_URL + Constants.API_POSTER_SIZE_W185 + posterPath)
                name.text = title
                sinopse.text = overview
            }
        }
    }
}

