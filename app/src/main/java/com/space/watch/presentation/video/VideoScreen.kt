package com.space.watch.presentation.video

import androidx.annotation.OptIn
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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.space.watch.R
import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Size
import com.space.watch.domain.model.Video
import com.space.watch.ui.theme.WatchTheme

@Preview
@Composable
fun VideoScreenPreview() {
    WatchTheme {
        VideoScreen(
            state = VideoScreenState.Content(
                video = Video(
                    id = String(),
                    title = "Video",
                    description = "Description",
                    content = String(),
                    size = Size(1280, 720),
                    image = String(),
                    imageSize = Size(),
                    creator = Creator(
                        id = String(),
                        name = "Creator",
                        description = String(),
                        image = String(),
                        cover = String(),
                        verified = true
                    ),
                    duration = 0
                )
            )
        )
    }
}

@Composable
fun VideoScreen(
    state: VideoScreenState,
    onBackButtonClick: () -> Unit = { },
    onVideoCreatorClick: (String) -> Unit = { }
) {
    Box {
        Scaffold {
            when (state) {
                is VideoScreenState.Content -> {
                    VideoScreenContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        video = state.video,
                        onVideoCreatorClick = onVideoCreatorClick
                    )
                }

                VideoScreenState.Wait -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                VideoScreenState.Empty -> Unit
            }
        }

        OutlinedIconButton(
            modifier = Modifier.padding(16.dp),
            colors = IconButtonDefaults.outlinedIconButtonColors(
                containerColor = Color.Black.copy(alpha = 0.2f),
                contentColor = Color.White
            ),
            border = BorderStroke(
                width = 2.dp,
                color = Color.White.copy(alpha = 0.1f),
            ),
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

@OptIn(UnstableApi::class)
@Composable
fun VideoContent(video: Video) {
    val context = LocalContext.current
    val exoPlayer = remember {
        val mediaItem = MediaItem.fromUri(video.content)
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            prepare()
        }
    }

    var lifecycleEvent by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        modifier = Modifier
            .aspectRatio(ratio = video.size.width.toFloat() / video.size.height.toFloat())
            .fillMaxWidth(),
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
                setShowRewindButton(false)
                setShowFastForwardButton(false)
            }
        },
        update = {
            when (lifecycleEvent) {
                Lifecycle.Event.ON_RESUME -> {
                    it.onResume()
                }

                Lifecycle.Event.ON_PAUSE -> {
                    it.onPause()
                    it.player?.pause()
                }

                else -> Unit
            }
        }
    )
}

@Composable
fun VideoInformation(video: Video, onVideoCreatorClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            VideoTitle(video.title)

            VideoFavoriteButton()
        }

        VideoCreator(
            creator = video.creator,
            onClick = {
                onVideoCreatorClick(video.creator.id)
            }
        )

        VideoDescription(video.description)
    }
}

@Composable
private fun RowScope.VideoTitle(title: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .weight(1f),
        text = title,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun VideoFavoriteButton() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
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
private fun VideoCreatorImage(image: String?) {
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
fun VideoCreatorVerifiedIcon() {
    Icon(
        imageVector = Icons.Outlined.Verified,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun VideoDescription(description: String) {
    if (description.isNotBlank()) {
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
