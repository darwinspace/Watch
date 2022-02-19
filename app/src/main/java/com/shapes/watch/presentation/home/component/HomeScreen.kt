package com.shapes.watch.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import com.shapes.watch.R
import com.shapes.watch.domain.model.HomeContent
import com.shapes.watch.domain.model.VideoInformation
import com.shapes.watch.presentation.home.HomeState
import com.shapes.watch.presentation.home.HomeViewModel
import com.shapes.watch.presentation.ui.WatchFloatingActionButton
import com.shapes.watch.presentation.ui.WatchTopBar
import com.shapes.watch.ui.theme.onSurfaceCarbon
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
private fun HomeScreenTopBar(onSearchClick: () -> Unit, onProfileClick: () -> Unit) {
    WatchTopBar(text = stringResource(id = R.string.app_name)) {
        IconButton(onClick = onSearchClick) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }

        IconButton(onClick = onProfileClick) {
            UserPhoto()
        }
    }
}

@Composable
private fun UserPhoto() {
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
            .size(32.dp)
    )
}

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            Column {
                HomeScreenTopBar(
                    onSearchClick = {
                        navController.navigate("search")
                    },
                    onProfileClick = {
                        navController.navigate("profile")
                    }
                )

                Divider()
            }
        },
        floatingActionButton = {
            Box(modifier = Modifier.padding(8.dp)) {
                WatchFloatingActionButton(
                    onClick = {
                        navController.navigate("create")
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) { contentPadding ->
        when (state) {
            is HomeState.Content -> {
                WatchContent(
                    navController = navController,
                    homeContent = state.homeContent,
                    contentPadding = contentPadding
                )
            }
            else -> Unit
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun Video(
    navController: NavHostController,
    video: VideoInformation
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        onClick = {
            navController.navigate("video")
        }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            VideoThumbnail(video.video.thumbnailUrl)

            Spacer(modifier = Modifier.height(12.dp))

            VideoDataContainer(navController, video)
        }
    }
}

@Composable
private fun VideoDataContainer(
    navController: NavHostController,
    video: VideoInformation
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            VideoCreatorPhoto(
                url = video.creator.photoUrl,
                onClick = {
                    navController.navigate("creator")
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            VideoTitle(video.video.title)
        }

        val t = 2.minutes + 184.seconds
        VideoDuration(t)
    }
}

@Composable
private fun VideoDuration(t: Duration) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.small.copy(CornerSize(4.dp))
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = t.toString(), style = MaterialTheme.typography.caption)
    }
}

@Composable
private fun VideoTitle(text: String) {
    Text(text = text, style = MaterialTheme.typography.body2)
}

@Composable
private fun VideoCreatorPhoto(url: String, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = rememberImagePainter(url),
            contentDescription = null,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurfaceCarbon,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .size(32.dp)
        )
    }
}

@Composable
private fun VideoThumbnail(videoThumbnailUrl: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.onSurfaceCarbon)
    ) {
        Image(
            painter = rememberImagePainter(
                data = videoThumbnailUrl,
                builder = {
                    size(OriginalSize)
                    scale(Scale.FIT)
                },
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 96.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun WatchContent(
    navController: NavHostController,
    homeContent: HomeContent,
    contentPadding: PaddingValues
) {
    Surface(modifier = Modifier.padding(contentPadding)) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(homeContent.videos) { video ->
                Video(
                    video = video,
                    navController = navController
                )
            }
        }
    }
}

//@ExperimentalMaterialApi
//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun DefaultPreview() {
//    WatchTheme {
//        HomeScreen()
//    }
//}
