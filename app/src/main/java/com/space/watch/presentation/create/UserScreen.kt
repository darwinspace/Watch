package com.space.watch.presentation.create

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.space.watch.presentation.component.WatchButton
import com.space.watch.presentation.component.WatchTopBar

//
//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun CreateScreenPreview() {
//    WatchTheme {
//        CreateScreen()
//    }
//}

@Composable
fun CreateScreenTopBar(
    uploadButtonEnabled: Boolean = false,
    onUploadClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    WatchTopBar(
        text = "Create",
        leadingContent = {
            IconButton(onClick = onCloseClick) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }
    ) {
        WatchButton(
            backgroundColor = MaterialTheme.colorScheme.primary,
            onClick = onUploadClick,
            enabled = uploadButtonEnabled
        ) {
            Text(text = "Upload")
            Spacer(modifier = Modifier.width(12.dp))
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        Spacer(modifier = Modifier.width(12.dp))

//        WatchIconButton(onClick = onCloseClick) {
//            Icon(imageVector = Icons.Default.Close, contentDescription = null)
//        }
    }
}


@Composable
fun UserScreen(
    viewModel: CreateViewModel,
    navController: NavHostController,
    userId: String
) {
    UserScreenContent(viewModel, userId, navController)
}


@Composable
private fun UserScreenContent(
    viewModel: CreateViewModel,
    userId: String,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
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
                /*TODO*/
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

        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Title (required)")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Description\n")
            }
        )
    }
}


@Composable
fun CreateVideoThumbnail(uri: Uri?, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.medium
            )
            .aspectRatio(ratio = 16f / 9f)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
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

            val icon = if (uri == null) Icons.Default.Warning else Icons.Default.Edit

            WatchButton(onClick = onClick) {
                Icon(imageVector = icon, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Thumbnail")
            }
        }
    }
}


@Composable
private fun CreateVideo(onClick: () -> Unit, uri: Uri?) {
    val b = uri == null
    Surface(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.medium
            )
            .aspectRatio(ratio = 16f / 9f)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = Color(0xFF3F51B5),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = uri),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            val icon = if (b) Icons.Default.Warning else Icons.Default.Edit

            WatchButton(onClick = onClick) {
                Icon(imageVector = icon, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Video")
            }
        }
    }
}
