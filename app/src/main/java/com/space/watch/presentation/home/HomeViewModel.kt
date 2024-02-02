package com.space.watch.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.data.repository.HomeRepositoryFirebaseImplementation
import com.space.watch.domain.model.HomeState
import com.space.watch.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    repository: HomeRepository = HomeRepositoryFirebaseImplementation()
) : ViewModel() {
    private val _content = MutableStateFlow<HomeState>(HomeState.Empty)
    val content = _content.asStateFlow()

    init {
        viewModelScope.launch {
            _content.value = HomeState.Wait
            _content.value = repository.getContent()
        }
    }
}
