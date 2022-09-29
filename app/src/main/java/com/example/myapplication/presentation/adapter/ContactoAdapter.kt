package com.example.myapplication.presentation.adapter

import com.example.myapplication.databinding.ItemContactoBinding
import com.example.myapplication.model.agenda.Contacto
import com.example.myapplication.presentation.base.BaseAdapter
import com.example.myapplication.presentation.base.BaseViewHolder
import com.example.myapplication.presentation.viewholder.ContactoViewHolder

class ContactoAdapter(
    override val onItemClicked: (data: Contacto) -> Unit
) : BaseAdapter<Contacto,ItemContactoBinding>(ItemContactoBinding::inflate) {
    override fun set(data: List<Contacto>) {
        submitList(data)

    }

    override fun viewHolder(binding: ItemContactoBinding): BaseViewHolder<Contacto> {
        return ContactoViewHolder(binding) { onItemClicked(getItem(it)) }
    }
}