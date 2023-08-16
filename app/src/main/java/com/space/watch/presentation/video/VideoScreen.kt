package com.space.watch.presentation.video

import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.space.watch.domain.model.User
import com.space.watch.domain.model.Video
import com.space.watch.domain.model.VideoInformation
import com.space.watch.presentation.component.WatchDescription
import com.space.watch.ui.theme.WatchTheme

@Preview
@Composable
fun VideoScreenPreview() {
    WatchTheme {
        VideoScreen(
            videoInformation = VideoInformation(
                video = Video(
                    id = "", title = "Video", description = "", thumbnailUrl = "", contentUrl = ""
                ), user = User(
                    id = "", name = "Name", description = "", photoUrl = "", coverUrl = ""
                )
            )
        )
    }
}

@Composable
fun VideoScreen(
    videoInformation: VideoInformation
) {
    val scrollState = rememberScrollState()
    Scaffold { padding ->
        VideoScreenContent(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(padding)
                .fillMaxSize(),
            videoInformation = videoInformation,
            onUserClick = { /*TODO*/ }
        )

        VideoScreenTopBar(onClose = { /*TODO*/ })
    }
}

@Composable
private fun VideoScreenTopBar(onClose: () -> Unit) {
    Box(modifier = Modifier.padding(24.dp)) {
        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}


@Composable
private fun VideoScreenContent(
    modifier: Modifier,
    videoInformation: VideoInformation,
    onUserClick: () -> Unit
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Video(videoInformation.video.contentUrl)

        VideoData(videoInformation = videoInformation, onUserClick = onUserClick)
    }
}


@Composable
private fun VideoData(
    videoInformation: VideoInformation,
    onUserClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VideoTitle(title = videoInformation.video.title)

        Spacer(modifier = Modifier.height(8.dp))

        VideoUser(
            user = videoInformation.user,
            onClick = onUserClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        videoInformation.video.description?.let { description ->
            WatchDescription(text = description)
        }
    }
}

@Composable
private fun VideoTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)
}


@Composable
private fun VideoUser(user: User, onClick: () -> Unit) {
    Surface(
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            VideoUserPhoto(user)

            Spacer(modifier = Modifier.width(12.dp))

            VideoUserName(user.name)
        }
    }
}

@Composable
private fun VideoUserPhoto(user: User) {
    Image(
        painter = rememberImagePainter(data = user.photoUrl),
        contentDescription = null,
        modifier = Modifier
            .clip(shape = CircleShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                shape = CircleShape
            )
            .size(28.dp)
    )
}

@Composable
private fun VideoUserName(name: String) {
    Text(text = name, style = MaterialTheme.typography.bodyMedium)
}

@Composable
private fun Video(contentUrl: String) {
    Surface(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )
            .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            .fillMaxWidth()
            .aspectRatio(ratio = 16f / 9f)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = {
                VideoView(it)
            }
        ) {
            it.setVideoPath(contentUrl)
            it.setMediaController(MediaController(it.context))
            it.requestFocus()
            it.start()
        }
    }
}
