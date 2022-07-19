package com.example.myapplication.presentation.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.MovieDetailFragmentBinding
import com.example.myapplication.extensions.collectLastestLyfeCycleFlow
import com.example.myapplication.extensions.showToast
import com.example.myapplication.model.movie.Movie
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.commons.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<MovieDetailViewModel, MovieDetailFragmentBinding>(MovieDetailFragmentBinding::inflate) {

    override val vm: MovieDetailViewModel by viewModels()
    private val args by navArgs<MovieDetailFragmentArgs>()
    override val TAG: String = MovieDetailFragment::class.java.name

    override fun setupUI() {
        super.setupUI()
        binding.vm = vm
        vm.getmovie(args.id)

    }

    override fun setupVM() {
        super.setupVM()
        requireActivity().collectLastestLyfeCycleFlow(vm.movieFlow) { uiState ->
            movieDetailState(uiState)
        }


    }

    private fun movieDetailState(uiState: UIState) {
        when (uiState) {
            is UIState.Success<*> -> binding.movie = uiState.data as Movie
            is UIState.Error ->  requireActivity().showToast(uiState.message!!)
            else ->{}

        }
    }

}