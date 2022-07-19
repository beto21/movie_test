package com.example.myapplication.presentation.viewholder

import com.example.myapplication.databinding.ItemContactoBinding
import com.example.myapplication.databinding.ItemMovieBinding
import com.example.myapplication.model.agenda.Contacto
import com.example.myapplication.presentation.base.BaseViewHolder

class ContactoViewHolder(private val binding: ItemContactoBinding,
                         override val onClick: (data: Int) -> Unit) : BaseViewHolder<Contacto>(binding) {
    override fun bindTo(data: Contacto) {
        binding.data = data
        binding.executePendingBindings()
    }
}