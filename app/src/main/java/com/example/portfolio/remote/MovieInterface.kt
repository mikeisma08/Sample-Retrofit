package com.example.portfolio.remote

import com.example.portfolio.datasource.MovieResponse
import com.example.portfolio.datasource.moviedetails.MovieDetails
import com.example.portfolio.datasource.moviedetails.Rating
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s") s: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String
        ): Response<MovieResponse>


    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") i: String,
        @Query("apikey") apiKey: String
    ): Response<MovieDetails>

}