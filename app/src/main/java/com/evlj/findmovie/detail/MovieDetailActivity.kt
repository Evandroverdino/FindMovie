package com.evlj.findmovie.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.evlj.findmovie.R
import com.evlj.findmovie.base.BaseActivity
import com.evlj.findmovie.databinding.ActivityMovieDetailBinding
import com.evlj.findmovie.model.MovieDetail
import com.evlj.findmovie.shared.Constants

class MovieDetailActivity : BaseActivity(), MovieDetailContract {

    companion object {
        @JvmStatic
        val MOVIE_ID = "movieId"

        @JvmStatic
        fun createIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            return intent
        }
    }

    private val presenter: MovieDetailPresenter by lazy {
        val presenter = MovieDetailPresenter()
        presenter.attachView(this)
        presenter
    }

    private val binding: ActivityMovieDetailBinding by lazy {
        DataBindingUtil.setContentView<ActivityMovieDetailBinding>(
            this, R.layout.activity_movie_detail
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        presenter.loadMovieDetails(
            intent.extras.getInt(MOVIE_ID),
            Constants.API_KEY, Constants.API_LANGUAGE
        )
    }

    override fun showMovieDetails(movieDetail: MovieDetail) {
        binding.movieDetail = movieDetail
        binding.posterPath = Constants.API_POSTER_URL +
                Constants.API_POSTER_SIZE_ORIGINAL +
                movieDetail.posterPath
    }

    override fun showProgressBar() {

    }

    override fun hideProgressBar() {

    }

}