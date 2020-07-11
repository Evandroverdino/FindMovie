package com.evlj.findmovie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import com.evlj.findmovie.R
import com.evlj.findmovie.base.activity.BaseActivity
import com.evlj.findmovie.model.PMovieDetail
import com.evlj.findmovie.shared.Constants
import com.evlj.findmovie.shared.extensions.loadImage
import com.evlj.findmovie.shared.extensions.makeGoneIf
import com.evlj.findmovie.shared.extensions.makeVisibleIf
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.ext.android.inject

class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {

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

    private val presenter by inject<MovieDetailPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        presenter.attachView(this)
        setupView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            NavUtils.navigateUpFromSameTask(this)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupView() {
        setupToolbar()
        intent.extras?.getInt(MOVIE_ID)?.let {
            presenter.loadMovieDetails(it, Constants.API_LANGUAGE)
        }
    }

    private fun setupToolbar() {
        with(toolbar) {
            setSupportActionBar(this)
            setNavigationIcon(R.drawable.round_arrow_back_white_24)
        }
    }

    override fun showMovieDetails(movieDetail: PMovieDetail) {
        with(movieDetail) {
            poster.loadImage(Constants.API_POSTER_URL + Constants.API_POSTER_SIZE_W342 + posterPath)
            movieName.text = title
            release.text = getString(R.string.movie_release, releaseDate)
            rating.text = getString(R.string.movie_rating, getVoteAverage())
            movieRuntime.text = getString(R.string.movie_runtime, getRuntime())
            movieGenres.text = getString(R.string.movie_genre, getGenres())
            description.text = overview
            favorite.setOnClickListener {
                presenter.saveOrDeleteFavoriteMovie(this)
            }
        }
    }

    override fun updateFavoriteView(isFavorite: Boolean) {
        with(favorite) {
            when (isFavorite) {
                true -> ContextCompat.getDrawable(
                    this@MovieDetailActivity,
                    R.drawable.round_star_black_48
                )
                false -> ContextCompat.getDrawable(
                    this@MovieDetailActivity,
                    R.drawable.round_star_border_black_48
                )
            }.let(::setImageDrawable)
        }
    }

    override fun showProgressBar() = setupVisibility(true)

    override fun hideProgressBar() = setupVisibility(false)

    private fun setupVisibility(isLoadingMovie: Boolean) {
        progressLoadingMovie.makeVisibleIf(isLoadingMovie)
        movieInfo.makeGoneIf(isLoadingMovie)
    }
}