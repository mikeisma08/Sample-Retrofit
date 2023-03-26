package com.example.portfolio.ui.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.portfolio.datasource.Movie
import com.example.portfolio.remote.MovieInterface
import com.example.portfolio.utils.Constant

class MoviePaging(private val s: String, private val movieInterface: MovieInterface) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
      return state.anchorPosition?.let{
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val page = params.key?:1

        return try {
            val data = movieInterface.getAllMovies(s,page,Constant.API_KEY)


            LoadResult.Page(
                data = data.body()?.Search!!,
                prevKey = if (page==1) null else page -1,
                nextKey = if(data.body()?.Search?.isEmpty()!!) null else page +1
            )

        }catch (e:java.lang.Exception){
            LoadResult.Error(e)
        }
    }
}