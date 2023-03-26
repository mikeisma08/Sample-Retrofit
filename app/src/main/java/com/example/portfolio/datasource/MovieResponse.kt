package com.example.portfolio.datasource

data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)