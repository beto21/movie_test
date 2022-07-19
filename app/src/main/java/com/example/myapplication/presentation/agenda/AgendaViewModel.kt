package com.example.myapplication.presentation.agenda

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.repository.AgendaRepository
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.error.ResourceNotFoundException
import com.example.myapplication.model.agenda.Contacto
import com.example.myapplication.model.movie.Movie
import com.example.myapplication.presentation.base.BaseViewModel
import com.example.myapplication.presentation.commons.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val agendaRepository: AgendaRepository
) : BaseViewModel() {

    private val _contactosFlow = MutableStateFlow<UIState>(UIState.Empty)
    val contactosFlow: StateFlow<UIState> = _contactosFlow.asStateFlow()

    fun getContactos() {
        viewModelScope.launch {
            agendaRepository.getContactos().catch {
                if (it !is ResourceNotFoundException) {
                    _contactosFlow.value = UIState.Error(it.message)
                }
            }.collectLatest {
                _contactosFlow.value = UIState.Success(it)

            }
        }
    }

    fun addContacto(contacto: Contacto){
        viewModelScope.launch {
            agendaRepository.insertContacto(contacto)
        }
    }






}