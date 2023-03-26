package com.example.portfolio.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolio.BR
import com.example.portfolio.databinding.MovieViewHolderBinding
import com.example.portfolio.datasource.Movie

class MoviePagingAdapter : PagingDataAdapter<Movie, MoviePagingAdapter.ViewHolder>(DIFF_UTIL) {


    var onClick : ((String)-> Unit)?=null


    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }


        }
    }

    fun onMovieClick(listener:(String)->Unit){
        onClick = listener
    }

    inner class ViewHolder(val viewHolderBinding: MovieViewHolderBinding) :
        RecyclerView.ViewHolder(viewHolderBinding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = getItem(position)
        holder.viewHolderBinding.setVariable(BR.movie, getItem(position))
        holder.viewHolderBinding.root.setOnClickListener {
           onClick?.let {
               it(data?.imdbID!!)
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MovieViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}