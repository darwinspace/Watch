package com.shapes.watch.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shapes.watch.ui.theme.onSurfaceCarbon

@Composable
fun WatchIconButton(borderWidth: Dp = 0.dp, onClick: () -> Unit, content: @Composable () -> Unit) {
    IconButton(
        modifier = Modifier
            .border(
                width = borderWidth,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.small
            )
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.surface),
        onClick = onClick,
        content = content
    )
}

@Composable
fun WatchFloatingActionButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.small
            ),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        onClick = onClick,
        content = content
    )
}
