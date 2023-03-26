package com.example.portfolio.remote

import com.example.portfolio.datasource.moviedetails.MovieDetails
import com.example.portfolio.utils.Constant
import com.example.portfolio.utils.Result


class MovieDetailsRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId : String): Result<MovieDetails>{

        return try{

            val response = movieInterface.getMovieDetails(imdbId,Constant.API_KEY)
            if (response.isSuccessful){

                Result(Result.Status.SUCCESS,response.body(),null)
            }
            else{
                Result(Result.Status.ERROR,null,null)

            }


        }catch(e:Exception){
            Result(Result.Status.ERROR,null,null)
        }
    }
}