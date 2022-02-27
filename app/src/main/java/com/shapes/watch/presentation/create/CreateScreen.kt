package com.shapes.watch.presentation.create

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.shapes.watch.domain.model.CreateVideoInformation
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
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
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
    viewModel: CreateViewModel = viewModel(),
    navController: NavHostController,
    creatorId: String
) {
    when (val state = viewModel.state.value) {
        is CreateState.Error -> {
            throw(state.exception)
        }
        CreateState.Success -> {
            navController.popBackStack()
        }
        CreateState.Loading -> {

        }
        else -> Unit
    }

    CreatorScreenContent(viewModel, creatorId, navController)
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
    val launcherVideoResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { videoUri = it }
    )

    val uploadButtonEnabled = title.isNotBlank() && description.isNotBlank() && videoUri != null

    Scaffold(
        topBar = {
            Column {
                CreateScreenTopBar(
                    uploadButtonEnabled = uploadButtonEnabled,
                    onUploadClick = {
                        videoUri?.let {
                            viewModel.uploadVideoInformation(
                                video = it,
                                videoInformation = CreateVideoInformation(
                                    creatorId = creatorId,
                                    title = title,
                                    description = description
                                ),
                                thumbnail = it
                            )
                        }
                    },
                    onCloseClick = {
                        navController.popBackStack()
                    }
                )

                Divider()
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
                }
            )
        }
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
    onVideoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        CreateVideo(onClick = onVideoClick, uri = videoUri)

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
private fun CreateVideo(onClick: () -> Unit, uri: Uri?) {
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
        color = MaterialTheme.colors.onSurfaceCarbon,
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

            val icon = if (uri == null) Icons.Default.Add else Icons.Default.Edit
            WatchIconButton(
                onClick = onClick
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }
}
