package com.space.watch.presentation.home

import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.watch.R
import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Size
import com.space.watch.domain.model.Video
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
                        content = String(),
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
                    )
                }
            )
        )
    }
}

@Composable
fun HomeScreen(
    state: HomeState,
    onCreateVideoClick: () -> Unit = { },
    onVideoCreatorClick: (String) -> Unit = { },
    onVideoClick: (String) -> Unit = { }
) {
    Scaffold(
        topBar = {
            HomeScreenTopBar()
        },
        floatingActionButton = {
            HomeScreenCreateVideoButton(onCreateVideoClick)
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

            HomeState.Wait -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            HomeState.Empty -> Unit
        }
    }
}

@Composable
private fun HomeScreenTopBar() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HomeScreenTopBarLogo()

                    HomeScreenTopBarTitle()
                }

                Row {
                    HomeScreenTopBarSearchButton()

                    HomeScreenTopBarUserButton()
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )
    }
}

@Composable
private fun HomeScreenTopBarLogo() {
    Image(
        modifier = Modifier.size(48.dp),
        painter = painterResource(R.drawable.icon_launcher_foreground),
        contentDescription = null
    )
}

@Composable
private fun HomeScreenTopBarTitle() {
    Text(
        text = stringResource(id = R.string.application_name),
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun HomeScreenTopBarSearchButton() {
    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )
    }
}

@Composable
private fun HomeScreenTopBarUserButton() {
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

@Composable
fun HomeScreenCreateVideoButton(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.padding(8.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        onClick = onClick
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
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 12.dp,
            end = 12.dp,
            bottom = 92.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
