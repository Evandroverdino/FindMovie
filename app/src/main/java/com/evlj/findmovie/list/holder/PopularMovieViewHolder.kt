package com.evlj.findmovie.list.holder

import br.com.ilhasoft.support.recyclerview.adapters.ViewHolder
import com.evlj.findmovie.databinding.ItemPopularMovieBinding
import com.evlj.findmovie.list.MainPresenter
import com.evlj.findmovie.model.Movie
import com.evlj.findmovie.shared.Constants

class PopularMovieViewHolder(
    private val binding: ItemPopularMovieBinding,
    private val presenter: MainPresenter
) :
    ViewHolder<Movie>(binding.root) {

    init {
        binding.presenter = presenter
    }

    override fun onBind(movie: Movie) {
        binding.movie = movie
        binding.posterPath = Constants.API_POSTER_URL +
                Constants.API_POSTER_SIZE_W500 + movie.posterPath
        binding.presenter = presenter
        binding.executePendingBindings()
    }

}
