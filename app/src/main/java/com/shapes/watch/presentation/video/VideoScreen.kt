package com.shapes.watch.presentation.video

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.model.VideoInformation
import com.shapes.watch.presentation.ui.Screen
import com.shapes.watch.presentation.ui.WatchDescription
import com.shapes.watch.ui.theme.onSurfaceCarbon

@ExperimentalMaterialApi
@Composable
fun VideoScreen(
    navController: NavHostController,
    videoInformation: VideoInformation
) {
    val scrollState = rememberScrollState()
    Scaffold {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Video()

            VideoData(
                videoInformation = videoInformation,
                onCreatorClick = {
                    navController.navigate(route = Screen.CreatorScreen.route + it.toRoute())
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun VideoData(
    videoInformation: VideoInformation,
    onCreatorClick: (Creator) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VideoTitle(title = videoInformation.video.title)

        Spacer(modifier = Modifier.height(8.dp))

        VideoCreator(
            creator = videoInformation.creator,
            onClick = onCreatorClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        WatchDescription(text = videoInformation.video.description)
    }
}

@Composable
private fun VideoTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.h5)
}

@ExperimentalMaterialApi
@Composable
private fun VideoCreator(creator: Creator, onClick: (Creator) -> Unit) {
    Card(
        elevation = 0.dp,
        onClick = {
            onClick(creator)
        }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VideoCreatorPhoto(creator)

            Spacer(modifier = Modifier.width(12.dp))

            VideoCreatorName(creator.name)
        }
    }
}

@Composable
private fun VideoCreatorPhoto(creator: Creator) {
    Image(
        painter = rememberImagePainter(data = creator.photoUrl),
        contentDescription = null,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = CircleShape
            )
            .clip(CircleShape)
            .size(28.dp)
    )
}

@Composable
private fun VideoCreatorName(name: String) {
    Text(text = name, style = MaterialTheme.typography.body2)
}

@Composable
private fun Video() {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.medium
            )
            .background(Color(0xFFD1C4E9))
            .aspectRatio(ratio = 16f / 9f)
            .fillMaxWidth()
    )
}

//@ExperimentalMaterialApi
//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun VideScreenPreview() {
//    WatchTheme {
//        VideoScreen(navController)
//    }
//}
