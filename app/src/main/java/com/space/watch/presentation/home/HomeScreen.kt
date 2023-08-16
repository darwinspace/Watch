package com.space.watch.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.space.watch.R
import com.space.watch.domain.model.User
import com.space.watch.domain.model.HomeContent
import com.space.watch.domain.model.Video
import com.space.watch.domain.model.VideoInformation
import com.space.watch.presentation.component.WatchTopBar
import com.space.watch.ui.theme.WatchTheme
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

@Preview
@Composable
fun HomeScreenPreview() {
    WatchTheme {
        HomeScreen(
            homeContent = HomeContent(
                videos = listOf(
                    VideoInformation(
                        video = Video(
                            id = "",
                            title = "Video",
                            description = "",
                            thumbnailUrl = "",
                            contentUrl = ""
                        ),
                        user = User(
                            id = "", name = "Name", description = "", photoUrl = "", coverUrl = ""
                        )
                    )
                )
            )
        )
    }
}

@Composable
fun HomeScreen(homeContent: HomeContent) {
    Scaffold(
        topBar = {
            HomeScreenTopBar(
                onSearchButtonClick = { /*TODO*/ },
                onUserPhotoClick = { /*TODO*/ },
            )
        }
    ) { padding ->
        HomeScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            content = homeContent
        )
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavHostController
) {
    // HomeScreen()
}

@Composable
private fun HomeScreenTopBar(onSearchButtonClick: () -> Unit, onUserPhotoClick: () -> Unit) {
    Column {
        WatchTopBar(text = stringResource(id = R.string.application_name)) {
            IconButton(onClick = onSearchButtonClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }

            IconButton(onClick = onUserPhotoClick) {
                UserPhoto()
            }
        }

        Divider()
    }
}

@Composable
private fun UserPhoto() {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                shape = CircleShape
            )
            .background(color = Color.Blue)
            .size(32.dp)
    )
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(42.dp), strokeWidth = 4.dp)
    }
}

@Composable
fun Video(
    videoInformation: VideoInformation,
    onClick: () -> Unit,
    onUserClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column {
            VideoThumbnail(videoInformation.video.thumbnailUrl)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    VideoUser(
                        user = videoInformation.user,
                        onClick = onUserClick
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    VideoTitle(videoInformation.video.title)
                }

                val duration = 2.minutes
                VideoDuration(duration)
            }
        }
    }
}

@Composable
private fun VideoDuration(duration: Duration) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.extraSmall
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = duration.toString(), style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
private fun VideoTitle(text: String) {
    Text(text = text, style = MaterialTheme.typography.bodyMedium)
}

@Composable
private fun VideoUser(user: User, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        VideoUserPhoto(user)
    }
}

@Composable
private fun VideoUserPhoto(user: User) {
    Image(
        painter = rememberAsyncImagePainter(user.photoUrl),
        contentDescription = null,
        modifier = Modifier
            .clip(shape = CircleShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                shape = CircleShape
            )
            .size(32.dp)
    )
}

@Composable
private fun VideoThumbnail(videoThumbnailUrl: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.medium
            )
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            .aspectRatio(ratio = 16f / 9f)
    ) {
        Image(
            painter = rememberAsyncImagePainter(videoThumbnailUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}


@Composable
fun HomeScreenContent(
    modifier: Modifier,
    content: HomeContent
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(content.videos) { video ->
            Video(
                videoInformation = video,
                onClick = { /*TODO*/ },
                onUserClick = { /*TODO*/ }
            )
        }
    }
}
