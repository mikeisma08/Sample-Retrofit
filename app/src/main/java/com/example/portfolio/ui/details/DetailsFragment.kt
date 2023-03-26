package com.example.portfolio.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.portfolio.databinding.FragmentDetailsBinding
import com.example.portfolio.ui.movie.MovieViewModel
import com.example.portfolio.utils.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    private val args : DetailsFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getMovieDetails(args.imdbId!!)

        viewModel.movieDetails.observe(viewLifecycleOwner){
            when(it.getContentIfNotHandled()?.status){
                Result.Status.LOADING->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                Result.Status.ERROR->{
                    binding.progressBar.visibility = View.GONE
                }
                Result.Status.SUCCESS->{
                    binding.progressBar.visibility = View.GONE
                    binding.movieDetails = it.peekContent().data
                }
                else -> {}
            }
        }
    }

}
