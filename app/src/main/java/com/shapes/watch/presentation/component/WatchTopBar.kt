package com.shapes.watch.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun WatchTopBar(
    text: String,
    leadingContent: (@Composable () -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    PaddingValues(
                        start = if (leadingContent == null) 24.dp else 12.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                leadingContent?.let {
                    it.invoke()
                    Spacer(modifier = Modifier.width(12.dp))
                }

                WatchTopBarNameText(text)
            }

            Row(content = content)
        }
    }
}

@Composable
fun WatchTopBarNameText(text: String, textStyle: TextStyle = MaterialTheme.typography.h6) {
    Row {
        Text(text = text, style = textStyle)

        Text(
            text = ".",
            style = textStyle,
            color = MaterialTheme.colors.primary
        )
    }
}
