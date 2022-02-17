package com.shapes.watch.presentation.video

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shapes.watch.R
import com.shapes.watch.presentation.ui.WatchDescription
import com.shapes.watch.ui.theme.WatchTheme
import com.shapes.watch.ui.theme.onSurfaceCarbon

@ExperimentalMaterialApi
@Composable
fun VideoScreen(
    videoViewModel: VideoViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    Surface {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Video()

            VideoData()
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun VideoData() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome 2022!", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(8.dp))

        VideoCreator(onClick = { /* TODO */ })

        Spacer(modifier = Modifier.height(12.dp))

        WatchDescription(text = "Hello Guys \uD83D\uDC4B\n\nI don't wanna sacrifice.")
    }
}

@ExperimentalMaterialApi
@Composable
private fun VideoCreator(onClick: () -> Unit) {
    Card(
        elevation = 0.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onSurfaceCarbon,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .size(28.dp)
            )

            Text(text = "Darwin Delgado", style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
private fun Video() {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.medium
            )
            .background(Color(0xFFD1C4E9))
            .aspectRatio(ratio = 16f / 9f)
            .fillMaxWidth()
    )
}

@ExperimentalMaterialApi
@Preview(showBackground = true, heightDp = 640, widthDp = 360)
@Composable
fun VideScreenPreview() {
    WatchTheme {
        VideoScreen()
    }
}

