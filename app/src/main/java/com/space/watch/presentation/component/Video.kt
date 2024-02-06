package com.space.watch.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
import coil.compose.AsyncImage
import com.space.watch.R
import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Size
import com.space.watch.domain.model.Video
import com.space.watch.ui.theme.WatchTheme

@Preview
@Composable
fun VideoPreview() {
    WatchTheme {
        Video(
            video = Video(
                id = String(),
                title = "Video",
                description = "Description",
                size = Size(1920, 1080),
                image = String(),
                imageSize = Size(1920, 1080),
                creator = Creator(
                    id = String(),
                    name = "Creator",
                    description = String(),
                    image = String(),
                    cover = String(),
                    verified = true
                ),
                duration = 0
            ),
            onCreatorClick = { throw NotImplementedError() },
            onClick = { throw NotImplementedError() }
        )
    }
}

@Composable
fun Video(
    video: Video,
    onCreatorClick: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            VideoImage(
                modifier = Modifier.padding(horizontal = 12.dp),
                videoImage = video.image,
                videoImageSize = video.imageSize
            )

            Row(
                modifier = Modifier
                    .padding(start = 4.dp, end = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                VideoCreatorImage(
                    creatorImage = video.creator.image,
                    onClick = onCreatorClick
                )

                VideoTitle(video.title)

                VideoDuration()
            }
        }
    }
}

@Composable
private fun VideoDuration() {
    Surface(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(0.2f)
        )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            text = "10:00",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.mono_medium)),
                fontWeight = FontWeight(500)
            )
        )
    }
}

@Composable
private fun RowScope.VideoTitle(title: String) {
    Text(
        modifier = Modifier.weight(1f),
        text = title,
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(Font(R.font.mono_medium)),
            fontWeight = FontWeight(500)
        ),
        maxLines = 1
    )
}

@Composable
private fun VideoCreatorImage(creatorImage: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(shape = CircleShape)
            .clickable(onClick = onClick)
            .padding(4.dp)
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
            model = creatorImage,
            contentDescription = null
        )
    }
}

@Composable
private fun VideoImage(modifier: Modifier, videoImage: String, videoImageSize: Size) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(0.2f)
        )
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .aspectRatio(ratio = videoImageSize.width.toFloat() / videoImageSize.height.toFloat())
                .fillMaxWidth(),
            model = videoImage,
            contentDescription = null
        )
    }
}
