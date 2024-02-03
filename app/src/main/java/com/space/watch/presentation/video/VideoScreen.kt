package com.space.watch.presentation.video

import android.content.res.Configuration
import android.widget.VideoView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.space.watch.R
import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Video
import com.space.watch.domain.model.VideoSize
import com.space.watch.ui.theme.WatchTheme

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoScreenPreview() {
    WatchTheme {
        VideoScreen(
            state = VideoState.Content(
                video = Video(
                    id = String(),
                    title = "Video",
                    description = "Description",
                    image = String(),
                    creator = Creator(
                        id = String(),
                        name = "Video Creator",
                        description = String(),
                        image = String()
                    ),
                    size = VideoSize(1920, 1080),
                    duration = 0
                )
            )
        )
    }
}

@Composable
fun VideoScreen(viewModel: VideoViewModel = viewModel(), onBackButtonClick: () -> Unit) {
    val videoState by viewModel.content.collectAsState()
    VideoScreen(state = videoState, onBackButtonClick = onBackButtonClick)
}

@Composable
fun VideoScreen(state: VideoState, onBackButtonClick: () -> Unit = { }) {
    Box {
        Scaffold {
            when (state) {
                is VideoState.Content -> {
                    VideoScreenContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        video = state.video
                    )
                }

                VideoState.Empty -> {}
                VideoState.Wait -> {}
            }
        }

        SmallFloatingActionButton(
            modifier = Modifier.padding(12.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            onClick = onBackButtonClick
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }
}

@Composable
private fun VideoScreenContent(
    modifier: Modifier,
    video: Video
) {
    Column(
        modifier = modifier
    ) {
        MainVideo(video)

        MainVideoDetail(video)
    }
}

@Composable
fun MainVideoDetail(video: Video) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp)
    ) {
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .weight(1f),
                        text = video.title,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.mono_medium)),
                            fontWeight = FontWeight(500)
                        )
                    )

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
                    }
                }

                Row(
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.large)
                        .clickable { /*TODO*/ }
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(32.dp),
                            model = video.creator.image,
                            contentDescription = null
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = video.creator.name,
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.mono_medium)),
                                fontWeight = FontWeight(500)
                            ),
                            maxLines = 1,
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        item {
            Column(modifier = Modifier.padding(12.dp)) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = video.description,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.mono_medium)),
                            fontWeight = FontWeight(500)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun MainVideo(video: Video) {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(0.2f)
            )
    ) {
        AndroidView(
            modifier = Modifier
                .aspectRatio(ratio = video.size.width.toFloat() / video.size.height.toFloat())
                .fillMaxWidth(),
            factory = {
                VideoView(it)
            }
        )
    }
}
