package com.example.myapplication.presentation.movies

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import com.example.myapplication.databinding.FragmentMoviesBinding
import com.example.myapplication.extensions.collectLastestLyfeCycleFlow
import com.example.myapplication.extensions.showToast
import com.example.myapplication.model.movie.Movie
import com.example.myapplication.model.movie.MoviePlayNow
import com.example.myapplication.presentation.adapter.LoadStateAdapter
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
    private lateinit var concatAdapter: ConcatAdapter
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

    companion object {
        @JvmStatic
        fun newInstance() = MoviesFragment()

    }
    override fun setupUI() {
        super.setupUI()
        binding.vm = vm
        initRecyclerview()
    }


    private fun initRecyclerview() {
        concatAdapter = moviePageAdapter.withLoadStateFooter(footer = LoadStateAdapter())
        binding.recycler.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(8)
            addItemDecoration(SpaceItemDecorationVertical(32))
            adapter = concatAdapter
        }



    }


    override fun setupVM() {
        super.setupVM()
        vm.getMoviesPosters()
        observerMoviesPage()
        observerPostersMovies()

        requireActivity().collectLastestLyfeCycleFlow(moviePageAdapter.loadStateFlow) {
            it.refresh is LoadState.Loading
            it.refresh !is LoadState.Loading
            it.refresh is LoadState.Error
        }

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