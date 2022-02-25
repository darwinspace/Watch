package com.shapes.watch.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shapes.watch.common.Resource
import com.shapes.watch.domain.use_case.home.GetHomeContent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel constructor(
    private val getHomeContent: GetHomeContent = GetHomeContent()
) : ViewModel() {
    private val _state = mutableStateOf<HomeState>(HomeState.Empty)
    val state: State<HomeState> = _state

    init {
        getContent()
    }

    private fun getContent() {
        getHomeContent().onEach {
            _state.value = when (it) {
                is Resource.Success -> HomeState.Content(it.data)
                is Resource.Loading -> HomeState.Loading
                is Resource.Error -> HomeState.Error(it.exception)
            }
        }.launchIn(viewModelScope)
    }
}
