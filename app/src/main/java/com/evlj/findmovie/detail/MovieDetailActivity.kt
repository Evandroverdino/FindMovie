package com.evlj.findmovie.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
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
        binding.presenter = presenter
        presenter.loadMovieDetails(
            intent.extras.getInt(MOVIE_ID),
            Constants.API_KEY, Constants.API_LANGUAGE
        )
    }

    override fun showMovieDetails(movieDetail: MovieDetail) {
        binding.movieDetail = movieDetail
        binding.posterPath = Constants.API_POSTER_URL +
                Constants.API_POSTER_SIZE_W342 +
                movieDetail.posterPath
    }

    override fun updateFavoriteView(isFavorite: Boolean) {
        binding.favorite.setImageDrawable(
            if (isFavorite) ContextCompat.getDrawable(this, R.drawable.round_star_black_48)
            else ContextCompat.getDrawable(this, R.drawable.round_star_border_black_48)
        )
    }

    override fun showProgressBar() = setupVisibility(true)

    override fun hideProgressBar() = setupVisibility(false)

    private fun setupVisibility(isLoadingMovie: Boolean) {
        binding.apply {
            loadingMovie.visibility = if (isLoadingMovie) View.VISIBLE else View.GONE
            movieInfo.visibility = if (isLoadingMovie) View.GONE else View.VISIBLE
        }
    }

}