package com.example.myapplication.repository;

import android.graphics.Movie;

public interface MoviesDBRepository {
    fun getMovies(): Call<Movies>
}
