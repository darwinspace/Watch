package com.shapes.watch.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shapes.watch.common.Resource
import com.shapes.watch.domain.case.home.GetHomeContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeContent: GetHomeContent
) : ViewModel() {
    private val _state = mutableStateOf<HomeState>(HomeState.Empty)
    val state: State<HomeState> = _state

    init {
        getContent()
    }

    private fun getContent() {
        getHomeContent().onEach {
            when (it) {
                is Resource.Success -> _state.value = HomeState.Content(it.data)
                is Resource.Loading -> _state.value = HomeState.Loading
                is Resource.Error -> _state.value = HomeState.Error(it.exception)
            }
        }.launchIn(viewModelScope)
    }
}
