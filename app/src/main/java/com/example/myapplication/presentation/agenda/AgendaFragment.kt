package com.example.myapplication.presentation.agenda

import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.AgendaFragmentBinding
import com.example.myapplication.extensions.collectLastestLyfeCycleFlow
import com.example.myapplication.extensions.showToast
import com.example.myapplication.model.agenda.Contacto
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.commons.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AgendaFragment : BaseFragment<AgendaViewModel, AgendaFragmentBinding>
    (AgendaFragmentBinding::inflate) {
    override val vm: AgendaViewModel by viewModels()
    override val TAG: String = AgendaFragment::class.java.name
    private val ContactoAdapter by lazy {
        com.example.myapplication.presentation.adapter.ContactoAdapter() {

        }
    }


    override fun setupUI() {
        super.setupUI()
        vm.getContactos()

        binding.button.setOnClickListener {
            if (validateCampos()) {
                vm.addContacto(
                    Contacto(
                        nombre = binding.include.nombre.text.toString(),
                        apellidos = binding.include.apellidos.text.toString(),
                        correo = binding.include.correo.text.toString(),
                        direccion = binding.include.direccion.text.toString(),
                        celular = binding.include.telefono.text.toString()
                    )
                )
            }
        }


    }

    fun validateCampos(): Boolean {
        var isValid = true
        val message = requireActivity().getString(R.string.empty_error)
        with(binding.include) {
            if (nombre.text.isNullOrEmpty()) {
                nombre.error = message
                isValid = isValid && false
            } else {
                nombre.error = null
            }
            if (apellidos.text.isNullOrEmpty()) {
                apellidos.error = message
                isValid = false

            } else {
                apellidos.error = null
            }
            if (correo.text.isNullOrEmpty()) {
                correo.error = message
                isValid = false

            } else {
                correo.error = null
            }
            if (direccion.text.isNullOrEmpty()) {
                direccion.error = message
                isValid = false


            } else {
                direccion.error = null
            }
            if (telefono.text.isNullOrEmpty()) {
                telefono.error = message
                isValid = false

            } else {
                telefono.error = null
            }

        }
        return isValid


    }


    @Suppress("UNCHECKED_CAST")
    private fun observerContactos() {
        requireActivity().collectLastestLyfeCycleFlow(vm.contactosFlow) {
            if (it is UIState.Success<*>) {
                ContactoAdapter.set(it.data as List<Contacto>)
            } else if (it is UIState.Error) {
                requireActivity().showToast(it.message!!)
            }

        }
    }


    override fun setupVM() {
        super.setupVM()
        observerContactos()
    }


}