package com.example.myapplication.presentation.home

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.HomeFragmentBinding
import com.example.myapplication.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>
    (HomeFragmentBinding::inflate) {
    override val vm: HomeViewModel by viewModels()
    override val TAG: String = HomeFragment::class.java.name


    override fun setupUI() {
        super.setupUI()
        binding.buttonAgenda.setOnClickListener { view->
           view.findNavController().navigate(R.id.agendaFragment)
        }
        binding.buttonMovies.setOnClickListener { view->
            view.findNavController().navigate(R.id.moviesFragment)

        }
    }





    override fun setupVM() {
        super.setupVM()



    }




}