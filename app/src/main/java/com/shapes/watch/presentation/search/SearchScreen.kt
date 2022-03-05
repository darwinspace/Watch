package com.shapes.watch.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shapes.watch.ui.theme.WatchTheme
import com.shapes.watch.ui.theme.onSurfaceCarbon

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
    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = Modifier.Companion
            .weight(1f),
        textStyle = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun SearchScreen() {
    var text by remember { mutableStateOf(String()) }
    val onTextChange: (String) -> Unit = { text = it }
    val onSearchClick = { /* TODO */ }
    Scaffold(
        topBar = {
            SearchTopBarContainer(text, onTextChange, onSearchClick)
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {

        }
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

@Preview(showBackground = true, heightDp = 640, widthDp = 360)
@Composable
fun SearchScreenPreview() {
    WatchTheme {
        SearchScreen()
    }
}
