package com.example.myapplication

import com.example.myapplication.data.Result

data class movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)