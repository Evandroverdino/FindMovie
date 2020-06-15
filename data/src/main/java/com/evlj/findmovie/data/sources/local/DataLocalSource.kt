package com.evlj.findmovie.data.sources.local

import com.evlj.findmovie.data.entities.DMovieDetail
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm

class DataLocalSource(
    private val realm: Realm
) : IDataLocalSource {

    override fun searchMovieInDatabase(movieId: Int): Single<DMovieDetail> {
        return Single.fromCallable {
            realm.where(DMovieDetail::class.java)
                .equalTo(DMovieDetail::id.name, movieId)
                .findAll()
                .first()
        }
    }

    override fun setMovieAsFavoriteOrNot(movieDetail: DMovieDetail): Completable {
        return Completable.fromCallable {
            with(movieDetail) {
                realm.executeTransaction {
                    isFavorite = isFavorite.not()
                    it.insertOrUpdate(this)
                }
            }
        }
    }
}