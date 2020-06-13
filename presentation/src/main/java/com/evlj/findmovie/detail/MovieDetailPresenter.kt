package com.evlj.findmovie.detail

import com.evlj.findmovie.base.BasePresenter
import com.evlj.findmovie.model.MovieDetail
import com.evlj.findmovie.network.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults

class MovieDetailPresenter : BasePresenter<MovieDetailContract>(MovieDetailContract::class.java) {

    private val movieService: MovieService by lazy { MovieService() }

    private val realm: Realm by lazy { Realm.getDefaultInstance() }

    fun loadMovieDetails(movieId: Int, apiKey: String, language: String) {
        val query = searchMovieInDatabase(movieId)

        if (query.isNotEmpty()) {
            query.first()?.let {
                if (it.isFavorite) loadFromRealm(it)
                else loadFromAPI(movieId, apiKey, language)
            }
        } else loadFromAPI(movieId, apiKey, language)
    }

    private fun loadFromRealm(movieDetail: MovieDetail) = movieDetail.let {
        view?.hideProgressBar()
        view?.showMovieDetails(it)
        view?.updateFavoriteView(it.isFavorite)
    }

    private fun loadFromAPI(movieId: Int, apiKey: String, language: String) =
        movieService.getMovieDetails(movieId, apiKey, language)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.showProgressBar() }
            .subscribe({
                view?.hideProgressBar()
                view?.showMovieDetails(it)
            }, {
                view?.hideProgressBar()
                view?.showMessage(it.message)
            })

    private fun searchMovieInDatabase(movieId: Int) =
        realm.where(MovieDetail::class.java)
            .equalTo(MovieDetail::id.name, movieId)
            .findAll()

    fun saveOrDeleteFavoriteMovie(movieDetail: MovieDetail) =
        setMovieAsFavoriteOrNot(searchMovieInDatabase(movieDetail.id), movieDetail)

    private fun setMovieAsFavoriteOrNot(
        query: RealmResults<MovieDetail>,
        movieDetail: MovieDetail
    ) {
        realm.executeTransaction {
            if (query.isNotEmpty()) query.first()?.let { movieDetail.isFavorite = it.isFavorite.not() }
            else movieDetail.isFavorite = query.isNotEmpty().not()

            it.insertOrUpdate(movieDetail)
        }
        view?.updateFavoriteView(movieDetail.isFavorite)
    }

}