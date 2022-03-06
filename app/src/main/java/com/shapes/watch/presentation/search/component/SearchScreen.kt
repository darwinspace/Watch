package com.shapes.watch.presentation.search.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shapes.watch.domain.model.SearchVideoContent
import com.shapes.watch.presentation.home.component.LoadingScreen
import com.shapes.watch.presentation.home.component.Video
import com.shapes.watch.presentation.search.SearchState
import com.shapes.watch.presentation.search.SearchViewModel
import com.shapes.watch.ui.theme.onSurfaceCarbon
import kotlinx.coroutines.launch

@Composable
fun SearchTopBar(text: String, onTextChange: (String) -> Unit, onSearchClick: () -> Unit) {
    Surface {
        Row(
            modifier = Modifier.padding(end = 16.dp, start = 24.dp, top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchTextField(text, onTextChange)

            SearchButton(text, onSearchClick)
        }
    }
}

@Composable
private fun SearchButton(text: String, onSearchClick: () -> Unit) {
    IconButton(
        enabled = text.isNotBlank(),
        onClick = onSearchClick
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    }
}

@Composable
private fun RowScope.SearchTextField(text: String, onTextChange: (String) -> Unit) {
    Box(modifier = Modifier.weight(1f)) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onSurface),
            cursorBrush = SolidColor(MaterialTheme.colors.primary)
        )

        if (text.isBlank()) {
            Text(
                text = "Search",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
                )
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    var text by remember { mutableStateOf(String()) }
    val onTextChange: (String) -> Unit = { text = it }
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            SearchTopBarContainer(
                text = text,
                onTextChange = onTextChange,
                onSearchClick = {
                    scope.launch {
                        viewModel.search(text)
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            when (state) {
                is SearchState.Content -> {
                    SearchScreenContent(state.searchVideoContent)
                }
                SearchState.Empty -> {
                    EmptySearchScreen()
                }
                is SearchState.Error -> TODO()
                SearchState.Loading -> {
                    LoadingScreen()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SearchScreenContent(searchVideoContent: SearchVideoContent) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(searchVideoContent.videos) { video ->
            Video(
                video = video,
                onClick = {

                },
                onCreatorClick = {

                }
            )
        }
    }
}

@Composable
fun EmptySearchScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Search a video", style = MaterialTheme.typography.h6)
    }
}

@Composable
private fun SearchTopBarContainer(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Column {
        SearchTopBar(
            text = text,
            onTextChange = onTextChange,
            onSearchClick = onSearchClick
        )

        Divider(color = MaterialTheme.colors.onSurfaceCarbon)
    }
}

//
//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun SearchScreenPreview() {
//    WatchTheme {
//        SearchScreen()
//    }
//}
