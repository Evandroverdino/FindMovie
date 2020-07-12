package com.evlj.findmovie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evlj.findmovie.R
import com.evlj.findmovie.base.activity.BaseActivity
import com.evlj.findmovie.base.adapter.AnyRvAdapter
import com.evlj.findmovie.base.adapter.AnyRvItemController
import com.evlj.findmovie.detail.MovieDetailActivity
import com.evlj.findmovie.list.listener.RecyclerScrollListener
import com.evlj.findmovie.model.PMovie
import com.evlj.findmovie.shared.Constants
import com.evlj.findmovie.shared.extensions.loadImage
import com.evlj.findmovie.shared.extensions.makeVisibleIf
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_popular_movie.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), MainContract.View {

    private var page: Int = 0
    private var totalResults: Int = 0

    private val mainViewModel by viewModel<MainViewModel>()

    private val moviesAdapter: AnyRvAdapter by lazy {
        AnyRvAdapter(mapOf(PMovie::class to PopularMovieItemController)) { _, item ->
            navigateToMovieDetail((item as PMovie).id)
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    override fun loadMorePopularMovies(pageResult: Int) {
        mainViewModel.loadPopularMovies(
            language = Constants.API_LANGUAGE,
            sortBy = Constants.API_SORT_BY,
            page = pageResult
        )
    }

    override fun navigateToMovieDetail(movieId: Int) {
        startActivity(MovieDetailActivity.createIntent(this, movieId))
    }

    private fun setupView() {
        setupAdapter()
        setupRecyclerView(recyclerViewMovies)
        with(mainViewModel) {
            getMovies().observe(this@MainActivity, Observer {
                moviesAdapter.add(it)
                moviesAdapter.notifyDataSetChanged()
            })
            getPageResults().observe(this@MainActivity, Observer {
                totalResults = it
            })
            getProgressState().observe(this@MainActivity, Observer {
                setupProgressVisibility(it)
            })
            getError().observe(this@MainActivity, Observer {
                showMessage(it.message)
            })
        }
    }

    private fun setupAdapter() {
        mainViewModel.loadPopularMovies(
            language = Constants.API_LANGUAGE,
            sortBy = Constants.API_SORT_BY,
            page = Constants.API_PAGE_RESULT
        )
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) = with(recyclerView) {
        layoutManager = setupLayoutManager()
        setHasFixedSize(true)
        adapter = moviesAdapter
        addOnScrollListener(getScrollListener())
    }

    private fun setupLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    private fun getScrollListener(): RecyclerScrollListener =
        object : RecyclerScrollListener() {

            override fun loadMoreMovies() {
                if (page + 1 <= totalResults) {
                    page = page.inc()
                    loadMorePopularMovies(page)
                }
            }
        }

    private fun setupProgressVisibility(isLoadingMovies: Boolean) {
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
                poster.loadImage(Constants.API_POSTER_URL + Constants.API_POSTER_SIZE_W185 + posterPath)
                name.text = title
                sinopse.text = overview
            }
        }
    }
}

