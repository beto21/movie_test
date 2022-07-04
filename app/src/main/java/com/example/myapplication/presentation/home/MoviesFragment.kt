package com.example.myapplication.presentation.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentMoviesBinding
import com.example.myapplication.extensions.collectLastestLyfeCycleFlow
import com.example.myapplication.extensions.showToast
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MoviePlayNow
import com.example.myapplication.presentation.adapter.LoadStateAdapter
import com.example.myapplication.presentation.adapter.MovieAdapter
import com.example.myapplication.presentation.adapter.MoviePageAdapter
import com.example.myapplication.presentation.adapter.MoviePosterAdapter
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.commons.SpaceItemDecorationVertical
import com.example.myapplication.presentation.commons.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesViewModel, FragmentMoviesBinding>
    (FragmentMoviesBinding::inflate) {
    override val vm: MoviesViewModel by viewModels()
    override val TAG: String = MoviesFragment::class.java.name
    private val moviePosterAdapter by lazy {
        MoviePosterAdapter { movie: MoviePlayNow ->
            val id = movie.id
            val action =
                MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(id.toInt())
            findNavController().navigate(action)
        }

    }
    private val moviePageAdapter by lazy {
        MoviePageAdapter { movie: Movie ->
            val id = movie.id
            val action =
                MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(id.toInt())
            findNavController().navigate(action)

        }
    }

    override fun setupUI() {
        super.setupUI()
        binding.vm = vm
        initRecyclerview()
    }


    private fun initRecyclerview() {
        moviePageAdapter.withLoadStateFooter(footer = LoadStateAdapter())
        binding.recycler.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(8)
            addItemDecoration(SpaceItemDecorationVertical(32))
            adapter = moviePageAdapter
        }
        binding.recyclerOne.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(8)
            adapter = moviePosterAdapter
        }


    }


    override fun setupVM() {
        super.setupVM()
        vm.getMoviesPosters()
        observerMoviesPage()
        observerPostersMovies()


    }


    private fun observerMoviesPage() {
        requireActivity().collectLastestLyfeCycleFlow(vm.movies) {
            moviePageAdapter.submitData(it)

        }
    }

    private fun observerPostersMovies() {
        requireActivity().collectLastestLyfeCycleFlow(vm.moviesPostersFlow) { uiState ->
            moviePosterState(uiState)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun moviePosterState(uiState: UIState) {
        when (uiState) {
            is UIState.Success<*> -> moviePosterAdapter.set(uiState.data as List<MoviePlayNow>)
            is UIState.Error -> requireActivity().showToast(uiState.message!!)
            else -> {}
        }

    }


}