package com.shapes.watch.presentation.creator.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.model.CreatorContent
import com.shapes.watch.presentation.creator.CreatorState
import com.shapes.watch.presentation.creator.CreatorViewModel
import com.shapes.watch.presentation.home.component.Video
import com.shapes.watch.presentation.component.WatchDescription
import com.shapes.watch.presentation.component.WatchIconButton
import com.shapes.watch.ui.theme.onSurfaceCarbon

@ExperimentalMaterialApi
@Composable
fun CreatorScreen(
    viewModel: CreatorViewModel = hiltViewModel(),
    navController: NavHostController,
    creator: Creator
) {
    LaunchedEffect(key1 = true) {
        viewModel.getContent(creator)
    }

    val state = viewModel.state.value

    Scaffold {
        when (state) {
            is CreatorState.Content -> CreatorScreenContent(
                navController,
                state.creatorContent,
                creator
            )
            else -> Unit
        }

        Box(modifier = Modifier.padding(24.dp)) {
            WatchIconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun CreatorScreenContent(
    navController: NavHostController,
    creatorContent: CreatorContent,
    creator: Creator
) {
    Surface {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CreatorData(creator)

                    Spacer(modifier = Modifier.height(12.dp))

                    CreatorName(creator.name)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${creatorContent.videos.size} Video",
                        style = MaterialTheme.typography.subtitle2
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    creator.description?.let { description ->
                        WatchDescription(text = description)
                    }
                }
            }

            items(creatorContent.videos) { video ->
                Video(
                    video = video,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun CreatorName(name: String) {
    Text(
        text = name,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h5
    )
}

@Composable
private fun CreatorData(creator: Creator) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .padding(bottom = 64.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurfaceCarbon,
                    shape = MaterialTheme.shapes.medium
                )
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.onSurfaceCarbon)
                .aspectRatio(16f / 9f)
        ) {
            Image(
                painter = rememberImagePainter(creator.coverUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        Image(
            painter = rememberImagePainter(data = creator.photoUrl),
            contentDescription = null,
            modifier = Modifier
                .border(
                    width = 8.dp,
                    color = MaterialTheme.colors.surface,
                    shape = CircleShape
                )
                .padding(8.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurfaceCarbon,
                    shape = CircleShape
                )
                .size(96.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}
