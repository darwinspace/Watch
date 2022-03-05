package com.shapes.watch.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WatchButton(
    backgroundColor: Color = MaterialTheme.colors.surface,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        enabled = enabled,
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        content = content
    )
}
