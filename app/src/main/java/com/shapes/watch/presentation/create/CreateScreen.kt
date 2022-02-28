package com.shapes.watch.presentation.create

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.presentation.home.component.LoadingScreen
import com.shapes.watch.presentation.ui.WatchIconButton
import com.shapes.watch.presentation.ui.WatchTextField
import com.shapes.watch.presentation.ui.WatchTopBar
import com.shapes.watch.ui.theme.onSurfaceCarbon

//@ExperimentalMaterialApi
//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun CreateScreenPreview() {
//    WatchTheme {
//        CreateScreen()
//    }
//}

@ExperimentalMaterialApi
@Composable
fun CreateScreenTopBar(
    uploadButtonEnabled: Boolean = false,
    onUploadClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    WatchTopBar(text = "Create") {
        Button(
            onClick = onUploadClick,
            enabled = uploadButtonEnabled,
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
        ) {
            Text(text = "Upload")
            Spacer(modifier = Modifier.width(12.dp))
            Icon(imageVector = Icons.Default.Upload, contentDescription = null)
        }

        Spacer(modifier = Modifier.width(12.dp))

        WatchIconButton(onClick = onCloseClick) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CreateScreen(
    viewModel: CreateViewModel = hiltViewModel(),
    navController: NavHostController,
    creatorId: String
) {
    CreatorScreenContent(viewModel, creatorId, navController)
    when (val state = viewModel.uploadState.value) {
        is UploadVideoState.Error -> {
            throw(state.exception)
        }
        UploadVideoState.Success -> {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Success!")
                }
            }
        }
        UploadVideoState.Loading -> {
            LoadingScreen()
            Text(text = "DARWIN ONLY WAITS!")
        }
        else -> Unit
    }
}

@ExperimentalMaterialApi
@Composable
private fun CreatorScreenContent(
    viewModel: CreateViewModel,
    creatorId: String,
    navController: NavHostController
) {
    var title by rememberSaveable { mutableStateOf(String()) }
    var description by rememberSaveable { mutableStateOf(String()) }

    var videoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var thumbnailUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcherVideoResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { videoUri = it }
    )
    val launcherImageResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { thumbnailUri = it }
    )

    val uploadButtonEnabled = title.isNotBlank() && description.isNotBlank() && videoUri != null

    Scaffold(
        topBar = {
            CreateScreenTopBarContainer(
                uploadButtonEnabled,
                onCloseClick = {
                    navController.popBackStack()
                }
            ) {
                viewModel.uploadVideoInformation(
                    video = requireNotNull(videoUri),
                    videoInformation = CreateVideoInformation(
                        creatorId = creatorId,
                        title = title,
                        description = description
                    ),
                    thumbnail = requireNotNull(thumbnailUri)
                )
            }
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            CreateScreenFields(
                title = title,
                onTitleChange = { title = it },
                description = description,
                onDescriptionChange = { description = it },
                videoUri = videoUri,
                onVideoClick = {
                    launcherVideoResult.launch("video/*")
                },
                thumbnailUri = thumbnailUri,
                onThumbnailClick = {
                    launcherImageResult.launch("image/*")
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun CreateScreenTopBarContainer(
    uploadButtonEnabled: Boolean,
    onCloseClick: () -> Unit,
    onUploadClick: () -> Unit
) {
    Column {
        CreateScreenTopBar(
            uploadButtonEnabled = uploadButtonEnabled,
            onUploadClick = onUploadClick,
            onCloseClick = onCloseClick
        )

        Divider()
    }
}

@ExperimentalMaterialApi
@Composable
private fun CreateScreenFields(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    videoUri: Uri?,
    onVideoClick: () -> Unit,
    onThumbnailClick: () -> Unit,
    thumbnailUri: Uri?
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        CreateVideo(onClick = onVideoClick, uri = videoUri)

        Spacer(modifier = Modifier.height(24.dp))

        CreateVideoThumbnail(onClick = onThumbnailClick, uri = thumbnailUri)

        Spacer(modifier = Modifier.height(24.dp))

        WatchTextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Title (required)")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        WatchTextField(
            value = description,
            onValueChange = onDescriptionChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Description\n")
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun CreateVideoThumbnail(uri: Uri?, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.medium
            )
            .aspectRatio(ratio = 16f / 9f)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberImagePainter(data = uri),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            val icon = if (uri == null) Icons.Default.Photo else Icons.Default.Edit
            WatchIconButton(
                onClick = onClick
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun CreateVideo(onClick: () -> Unit, uri: Uri?) {
    val b = uri == null
    val color = if (b) MaterialTheme.colors.onSurfaceCarbon else Color(0xFF3F51B5)
    Surface(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.medium
            )
            .aspectRatio(ratio = 16f / 9f)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = color,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberImagePainter(data = uri),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            val icon = if (b) Icons.Default.Movie else Icons.Default.Edit
            WatchIconButton(
                onClick = onClick
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }
}
