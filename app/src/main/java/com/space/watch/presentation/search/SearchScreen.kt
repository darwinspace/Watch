package com.space.watch.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.space.watch.domain.model.SearchVideoContent
import com.space.watch.presentation.home.LoadingScreen
import com.space.watch.presentation.home.Video

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Surface {
        Row(
            modifier = Modifier.padding(end = 16.dp, start = 12.dp, top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onCloseClick
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }

                Spacer(modifier = Modifier.width(12.dp))

                SearchTextField(
                    text = text,
                    onTextChange = onTextChange,
                    modifier = Modifier.fillMaxWidth()
                )
            }

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
private fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit
) {
    Box {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            modifier = modifier,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
        )

        if (text.isBlank()) {
            Text(
                text = "Search",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}


@Composable
fun SearchScreen(viewModel: SearchViewModel, navController: NavHostController) {
    var text by remember { mutableStateOf(String()) }
    //val state = viewModel.state.value
    Scaffold(
        topBar = {
            SearchTopBarContainer(
                text = text,
                onTextChange = { text = it },
                onCloseClick = {
                    navController.popBackStack()
                },
                onSearchClick = {
                    // viewModel.search(text)
                }
            )
        }
    ) { padding ->
        padding
        /*SearchScreenContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            searchVideoContent = state.searchVideoContent,
            navController = navController
        )*/
    }
}


@Composable
fun SearchScreenContent(
    modifier: Modifier,
    searchVideoContent: SearchVideoContent,
    navController: NavHostController
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(searchVideoContent.videos) { video ->
            Video(
                videoInformation = video,
                onClick = { /*TODO*/ },
                onUserClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun EmptySearchScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Search a video", style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
private fun SearchTopBarContainer(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Column {
        SearchTopBar(
            text = text,
            onTextChange = onTextChange,
            onCloseClick = onCloseClick,
            onSearchClick = onSearchClick
        )

        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
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
