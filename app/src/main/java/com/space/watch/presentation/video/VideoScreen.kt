package com.space.watch.presentation.video

import android.content.res.Configuration
import android.widget.VideoView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                        image = String(),
                        cover = String()
                    ),
                    size = VideoSize(1920, 1080),
                    duration = 0
                )
            )
        )
    }
}

@Composable
fun VideoScreen(
    state: VideoState,
    onBackButtonClick: () -> Unit = { },
    onVideoCreatorClick: (String) -> Unit = { }
) {
    Box {
        Scaffold {
            when (state) {
                is VideoState.Content -> {
                    VideoScreenContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        video = state.video,
                        onVideoCreatorClick = onVideoCreatorClick
                    )
                }

                VideoState.Empty -> {}
                VideoState.Wait -> {}
            }
        }

        SmallFloatingActionButton(
            modifier = Modifier.padding(12.dp),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.surface,
            onClick = onBackButtonClick
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
        }
    }
}

@Composable
private fun VideoScreenContent(
    modifier: Modifier,
    video: Video,
    onVideoCreatorClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        item {
            Column {
                VideoContent(video)

                VideoInformation(video, onVideoCreatorClick)
            }
        }
    }
}

@Composable
fun VideoInformation(video: Video, onVideoCreatorClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(12.dp)) {
        VideoTitle(video.title)

        VideoCreator(video.creator) {
            onVideoCreatorClick(video.creator.id)
        }

        VideoDescription(video)
    }
}

@Composable
private fun VideoTitle(title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f),
            text = title,
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
}

@Composable
private fun VideoCreator(creator: Creator, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.large)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VideoCreatorImage(creator)

        VideoCreatorName(creator)

        Icon(
            imageVector = Icons.Outlined.Verified,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun RowScope.VideoCreatorName(creator: Creator) {
    Text(
        modifier = Modifier.Companion
            .weight(1f)
            .padding(horizontal = 12.dp),
        text = creator.name,
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(Font(R.font.mono_medium)),
            fontWeight = FontWeight(500)
        ),
        maxLines = 1,
    )
}

@Composable
private fun VideoCreatorImage(creator: Creator) {
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
            model = creator.image,
            contentDescription = null
        )
    }
}

@Composable
private fun VideoDescription(video: Video) {
    Surface(
        modifier = Modifier.padding(12.dp),
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

@Composable
fun VideoContent(video: Video) {
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
