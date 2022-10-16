package com.example.myapplication.viewmodel

import com.example.myapplication.repository.MoviesDBRepository
import com.example.myapplication.repository.MoviesDBRepositoryImpl

class MoviesViewModel {
    fun getMovies() {
        val response = MoviesDBRepositoryImpl.getMovies()
    }
}