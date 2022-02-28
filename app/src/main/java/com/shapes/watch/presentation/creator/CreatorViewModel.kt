package com.shapes.watch.presentation.creator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shapes.watch.common.Resource
import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.use_case.creator.GetCreatorContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreatorViewModel @Inject constructor(
    private val getCreatorContent: GetCreatorContent/* = GetCreatorContent()*/
) : ViewModel() {
    private val _state = mutableStateOf<CreatorState>(CreatorState.Empty)
    val state: State<CreatorState> = _state

    fun getContent(creator: Creator) {
        getCreatorContent(creator).onEach {
            _state.value = when (it) {
                is Resource.Success -> CreatorState.Content(it.data)
                is Resource.Loading -> CreatorState.Loading
                is Resource.Error -> CreatorState.Error(it.exception)
            }
        }.launchIn(viewModelScope)
    }
}
