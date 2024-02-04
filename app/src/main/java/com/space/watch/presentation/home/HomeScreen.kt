package com.space.watch.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.watch.R
import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Video
import com.space.watch.domain.model.VideoSize
import com.space.watch.presentation.component.Video
import com.space.watch.ui.theme.WatchTheme

@Preview
@Composable
fun HomeScreenPreview() {
    WatchTheme {
        HomeScreen(
            state = HomeState.Content(
                videos = List(10) {
                    Video(
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
                }
            )
        )
    }
}

@Composable
fun HomeScreen(
    state: HomeState,
    onVideoCreatorClick: (String) -> Unit = { },
    onVideoClick: (String) -> Unit = { }
) {
    Scaffold(
        topBar = {
            HomeScreenTopBar()
        },
        floatingActionButton = {
            HomeScreenCreateVideoButton()
        }
    ) {
        when (state) {
            is HomeState.Content -> {
                HomeScreenContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    videos = state.videos,
                    onVideoCreatorClick = onVideoCreatorClick,
                    onVideoClick = onVideoClick
                )
            }

            HomeState.Empty -> Unit
            HomeState.Wait -> Unit
        }
    }
}

@Composable
fun HomeScreenCreateVideoButton() {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.surface,
        onClick = { /*TODO*/ }
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    videos: List<Video>,
    onVideoCreatorClick: (String) -> Unit,
    onVideoClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(videos) { video ->
            Video(
                video = video,
                onCreatorClick = {
                    onVideoCreatorClick(video.creator.id)
                },
                onClick = {
                    onVideoClick(video.id)
                }
            )
        }
    }
}


@Composable
private fun HomeScreenTopBar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeScreenTopBarApplicationLogo()

                HomeScreenTopBarActions()
            }
        }

        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )
    }
}

@Composable
private fun HomeScreenTopBarActions() {
    Row {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }

        UserImage()
    }
}

@Composable
private fun HomeScreenTopBarApplicationLogo() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(R.drawable.icon_launcher_foreground),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.application_name),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun UserImage() {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(shape = CircleShape)
            .clickable { /*TODO*/ }
            .padding(4.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                shape = CircleShape
            )
            .size(32.dp)
    )
}