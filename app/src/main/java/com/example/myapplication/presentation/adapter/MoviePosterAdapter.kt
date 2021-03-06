package com.example.myapplication.presentation.adapter

import com.example.myapplication.databinding.ItemPortadaBinding
import com.example.myapplication.presentation.base.BaseAdapter
import com.example.myapplication.presentation.base.BaseViewHolder
import com.example.myapplication.presentation.viewholder.MoviePostherViewHolder
import com.example.myapplication.model.movie.MoviePlayNow

class MoviePosterAdapter( override var onItemClicked: (data: MoviePlayNow) -> Unit ) : BaseAdapter<MoviePlayNow,ItemPortadaBinding>(ItemPortadaBinding::inflate) {


    override fun set(data: List<MoviePlayNow>) {
       submitList(data)
    }

    override fun viewHolder(binding: ItemPortadaBinding): BaseViewHolder<MoviePlayNow> {
        return MoviePostherViewHolder(binding) { onItemClicked(getItem(it)) }

    }
}