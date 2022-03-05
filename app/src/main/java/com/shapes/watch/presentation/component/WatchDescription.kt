package com.shapes.watch.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shapes.watch.ui.theme.onSurfaceCarbon

@Composable
fun WatchDescription(text: String) {
    Surface(
        modifier = Modifier,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurfaceCarbon
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            text = text,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Justify
        )
    }
}
