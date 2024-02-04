package com.space.watch.presentation.video

import android.widget.VideoView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
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

@Preview
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
                        name = "Creator",
                        description = String(),
                        image = String(),
                        cover = String(),
                        verified = true
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

                VideoState.Wait -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                VideoState.Empty -> Unit
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

        VideoCreator(
            creator = video.creator,
            onClick = {
                onVideoCreatorClick(video.creator.id)
            }
        )

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
    Surface(
        shape = MaterialTheme.shapes.large,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VideoCreatorImage(creator.image)

            VideoCreatorName(creator.name)

            if (creator.verified) {
                VideoCreatorVerifiedIcon()
            }
        }
    }
}

@Composable
fun VideoCreatorVerifiedIcon() {
    Icon(
        imageVector = Icons.Outlined.Verified,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun RowScope.VideoCreatorName(name: String) {
    Text(
        modifier = Modifier.weight(1f),
        text = name,
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
private fun VideoCreatorImage(image: String) {
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
            model = image,
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
