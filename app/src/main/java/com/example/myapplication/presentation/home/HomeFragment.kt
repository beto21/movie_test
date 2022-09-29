package com.example.myapplication.presentation.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.HomeFragmentBinding
import com.example.myapplication.presentation.adapter.PagerStateAdapter
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.movies.MoviesFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>
    (HomeFragmentBinding::inflate) {
    override val vm: HomeViewModel by viewModels()
    override val TAG: String = HomeFragment::class.java.name
    private var pageAdapterFrags: PagerStateAdapter? = null




    override fun setupUI() {
        super.setupUI()
        pageAdapterFrags = PagerStateAdapter(activity)
        pageAdapterFrags!!.addFragment(MoviesFragment::class.java, "Peliculas")


        binding.pager.adapter = pageAdapterFrags

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = pageAdapterFrags!!.titles[position]
        }.attach()

    }




}