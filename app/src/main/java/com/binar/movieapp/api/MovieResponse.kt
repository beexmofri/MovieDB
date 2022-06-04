package com.binar.movieapp.api

import com.binar.movieapp.data.model.Movie

data class MovieResponse(
    val results: List<Movie>
)