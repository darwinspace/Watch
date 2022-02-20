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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shapes.watch.R
import com.shapes.watch.domain.model.CreatorContent
import com.shapes.watch.presentation.creator.CreatorState
import com.shapes.watch.presentation.creator.CreatorViewModel
import com.shapes.watch.presentation.home.component.Video
import com.shapes.watch.presentation.ui.WatchDescription
import com.shapes.watch.presentation.ui.WatchIconButton
import com.shapes.watch.ui.theme.WatchTheme
import com.shapes.watch.ui.theme.onSurfaceCarbon

@ExperimentalMaterialApi
@Composable
fun CreatorScreen(
    viewModel: CreatorViewModel = viewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value

    Scaffold {
        when (state) {
            is CreatorState.Content -> CreatorScreenContent(
                state.creatorContent,
                navController
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
    creatorContent: CreatorContent,
    navController: NavHostController
) {
    Surface {
        LazyColumn(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CreatorData()

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Darwin Delgado",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${creatorContent.videos.size} Video",
                        style = MaterialTheme.typography.subtitle2
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    WatchDescription(text = "Looking back")
                }
            }

            items(creatorContent.videos) { video ->
                Video(
                    video = video,
                    navController = navController
                )
            }
            // CreatorVideos(creatorContent, navController)
        }
    }
}

//@ExperimentalMaterialApi
//@Composable
//fun CreatorVideos(
//    creatorContent: CreatorContent,
//    navController: NavHostController
//) {
//    LazyColumn(
//        modifier = Modifier.fillMaxWidth(),
//        contentPadding = PaddingValues(12.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(creatorContent.videos) { video ->
//            Video(
//                video = video,
//                navController = navController
//            )
//        }
//    }
//}

@Composable
private fun CreatorData() {
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
                .aspectRatio(ratio = 16f / 9f)
                .background(MaterialTheme.colors.onSurfaceCarbon)
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .border(
                    width = 8.dp,
                    color = MaterialTheme.colors.surface,
                    shape = CircleShape
                )
                .padding(8.dp)
                .clip(CircleShape)
                .size(96.dp)
        )
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, heightDp = 640, widthDp = 360)
@Composable
fun CreatorScreenPreview() {
    WatchTheme {
        CreatorScreen(navController = rememberNavController())
    }
}
