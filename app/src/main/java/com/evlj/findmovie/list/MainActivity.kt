package com.evlj.findmovie.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.ilhasoft.support.recyclerview.adapters.AutoRecyclerAdapter
import br.com.ilhasoft.support.recyclerview.adapters.OnCreateViewHolder
import com.evlj.findmovie.R
import com.evlj.findmovie.base.BaseActivity
import com.evlj.findmovie.databinding.ActivityMainBinding
import com.evlj.findmovie.databinding.ItemPopularMovieBinding
import com.evlj.findmovie.list.holder.PopularMovieViewHolder
import com.evlj.findmovie.model.Movie
import com.evlj.findmovie.shared.Constants

class MainActivity : BaseActivity(), MainContract {

    private val presenter: MainPresenter by lazy {
        val presenter = MainPresenter()
        presenter.attachView(this)
        presenter
    }

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    private val popularMovieViewHolder:
            OnCreateViewHolder<Movie, PopularMovieViewHolder> by lazy {
        OnCreateViewHolder { layoutInflater, parent, _ ->
            PopularMovieViewHolder(
                ItemPopularMovieBinding.inflate(layoutInflater, parent, false),
                presenter
            )
        }
    }

    private val moviesAdapter:
            AutoRecyclerAdapter<Movie, PopularMovieViewHolder> by lazy {
        AutoRecyclerAdapter(mutableListOf(), popularMovieViewHolder)
            .apply { setHasStableIds(true) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupAdapter()
        setupRecyclerView(binding.movies)
    }

    private fun setupAdapter() {
        presenter.loadPopularMovies(
            Constants.API_KEY, Constants.API_LANGUAGE,
            Constants.API_SORT_BY, false, false,
            Constants.API_PAGE_RESULT
        )
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) = with(recyclerView) {
        layoutManager = setupLayoutManager()
        setHasFixedSize(true)
        adapter = moviesAdapter
        addOnScrollListener(presenter.getScrollListener())
    }

    private fun setupLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun fillPopularMovies(results: List<Movie>) {
        moviesAdapter.addAll(results)
        moviesAdapter.notifyDataSetChanged()
    }

    override fun loadMorePopularMovies(pageResult: Int) {
        presenter.loadPopularMovies(
            Constants.API_KEY, Constants.API_LANGUAGE,
            Constants.API_SORT_BY, false, false, pageResult
        )
    }

    override fun navigateToMovieDetail(movieId: Int) {

    }

}

