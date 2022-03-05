package com.shapes.watch.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shapes.watch.common.Resource
import com.shapes.watch.domain.use_case.search.SearchVideo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchContent: SearchVideo
) : ViewModel() {
    private val _state = mutableStateOf<SearchState>(SearchState.Empty)
    val state: State<SearchState> = _state

    fun search(value: String) {
        searchContent(value).onEach { resource ->
            _state.value = when (resource) {
                is Resource.Success -> SearchState.Content(resource.data)
                is Resource.Loading -> SearchState.Loading
                is Resource.Error -> throw resource.exception
            }
        }.launchIn(viewModelScope)
    }
}
