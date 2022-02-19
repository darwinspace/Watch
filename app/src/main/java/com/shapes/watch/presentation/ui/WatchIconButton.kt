package com.shapes.watch.presentation.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.shapes.watch.ui.theme.onSurfaceCarbon

@ExperimentalMaterialApi
@Composable
fun WatchIconButton(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(0.dp),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = shape
            )
            .clip(shape),
        shape = shape,
        elevation = elevation.elevation(interactionSource).value,
        onClick = onClick,
        role = Role.Button,
        interactionSource = interactionSource,
        indication = rememberRipple()
    ) {
        val size = 48.dp
        Box(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = size,
                    minHeight = size
                ),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

@Composable
fun WatchFloatingActionButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
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
