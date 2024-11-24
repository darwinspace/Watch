package com.space.watch.presentation.creator

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.space.watch.R
import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Size
import com.space.watch.domain.model.VideoDuration
import com.space.watch.domain.model.VideoInformation
import com.space.watch.presentation.component.Video
import com.space.watch.presentation.`interface`.theme.WatchTheme

@Preview
@Composable
fun CreatorScreenPreview() {
    WatchTheme {
        CreatorScreen(
            state = CreatorScreenState.Content(
                creator = Creator(
                    id = String(),
                    name = "Creator",
                    description = "Description",
                    image = String(),
                    cover = "Cover",
                    verified = true
                ),
                videos = List(10) {
                    VideoInformation(
                        id = String(),
                        title = "Video",
                        description = "Description",
                        content = String(),
                        size = Size(1920, 1080),
                        image = String(),
                        imageSize = Size(1920, 1080),
                        creator = Creator(
                            id = String(),
                            name = "Video Creator",
                            description = String(),
                            image = String(),
                            cover = String(),
                            verified = true,
                        ),
                        duration = VideoDuration(0, 0, 0)
                    )
                }
            )
        )
    }
}

@Composable
fun CreatorScreen(
    state: CreatorScreenState = CreatorScreenState.Empty,
    onBackButtonClick: () -> Unit = { },
    onVideoClick: (String) -> Unit = { }
) {
    Box {
        Scaffold { padding ->
            AnimatedContent(targetState = state, label = "CreatorScreenAnimation") { state ->
                when (state) {
                    is CreatorScreenState.Content -> {
                        CreatorScreenContent(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding),
                            creator = state.creator,
                            videos = state.videos,
                            onVideoClick = onVideoClick
                        )
                    }

                    CreatorScreenState.Wait -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    CreatorScreenState.Empty -> Unit
                }
            }
        }

        CreatorScreenBackButton(onBackButtonClick)
    }
}

@Composable
private fun CreatorScreenBackButton(onBackButtonClick: () -> Unit) {
    OutlinedIconButton(
        modifier = Modifier.padding(16.dp),
        colors = IconButtonDefaults.outlinedIconButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        ),
        onClick = onBackButtonClick
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
    }
}

@Composable
private fun CreatorScreenContent(
    modifier: Modifier,
    creator: Creator,
    videos: List<VideoInformation>,
    onVideoClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CreatorHeader(creator)

                CreatorName(creator.name)

                CreatorDescription(creator.description)
            }
        }

        item {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Videos",
                    style = MaterialTheme.typography.bodyMedium
                )

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = videos.size.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        items(videos) { video ->
            Video(
                videoInformation = video,
                onCreatorClick = { /*TODO*/ },
                onClick = { onVideoClick(video.id) }
            )
        }
    }
}

@Composable
private fun CreatorHeader(creator: Creator) {
    Column(
        verticalArrangement = Arrangement.spacedBy((-96 / 2).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreatorCover(creator.cover)

        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            CreatorImage(creator.image)

            CreatorVerifiedIcon(creator.verified)
        }
    }
}

@Composable
private fun CreatorCover(cover: String?) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .aspectRatio(ratio = 2f)
                .fillMaxWidth(),
            model = cover,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CreatorImage(image: String?) {
    Surface(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(96.dp),
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CreatorVerifiedIcon(verified: Boolean) {
    if (verified) {
        Surface(
            shape = CircleShape,
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun CreatorName(name: String) {
    Text(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        text = name,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun CreatorDescription(description: String) {
    if (description.isNotBlank()) {
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
                text = description,
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
