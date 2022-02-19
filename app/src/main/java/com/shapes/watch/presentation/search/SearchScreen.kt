package com.shapes.watch.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier
                    .weight(1f),
                textStyle = MaterialTheme.typography.subtitle1
            )

            IconButton(
                enabled = text.isNotBlank(),
                onClick = onSearchClick
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
    }
}

@Composable
fun SearchScreen() {
    var text by remember { mutableStateOf("Text") }
    Scaffold(
        topBar = {
            Column {
                SearchTopBar(
                    text = text,
                    onTextChange = { text = it },
                    onSearchClick = { /* TODO */ }
                )

                Divider(color = MaterialTheme.colors.onSurfaceCarbon)
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {

        }
    }
}

@Preview(showBackground = true, heightDp = 640, widthDp = 360)
@Composable
fun SearchScreenPreview() {
    WatchTheme {
        SearchScreen()
    }
}
