package com.binar.movieapp.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.binar.movieapp.data.local.FavoriteMovieRepository

class FavoriteViewModel @ViewModelInject constructor(
    private val repository: FavoriteMovieRepository
) : ViewModel(){
    val movies = repository.getFavoriteMovies()
}