package com.space.watch.presentation.create_video

import android.net.Uri
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.VideoOnly
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.space.watch.domain.model.Size
import com.space.watch.ui.theme.WatchTheme
import com.space.watch.util.getImageSize
import com.space.watch.util.getVideoSize

@Preview
@Composable
fun CreateVideoScreenPreview() {
    WatchTheme {
        CreateVideoScreen(
            state = CreateVideoState.Empty,
            videoTitle = String(),
            onVideoTitleChange = { },
            videoDescription = String(),
            onVideoDescriptionChange = { },
            isCreateVideoButtonEnabled = true
        )
    }
}

@Composable
fun CreateVideoScreen(
    state: CreateVideoState,
    videoTitle: String,
    onVideoTitleChange: (String) -> Unit,
    videoDescription: String,
    onVideoDescriptionChange: (String) -> Unit,
    videoUri: Uri? = null,
    onVideoSelected: (Uri?) -> Unit = { },
    videoSize: Size? = null,
    onVideoSizeChange: (Size?) -> Unit = { },
    videoImageUri: Uri? = null,
    onVideoImageSelected: (Uri?) -> Unit = { },
    videoImageSize: Size? = null,
    onVideoImageSizeChange: (Size?) -> Unit = { },
    isCreateVideoButtonEnabled: Boolean,
    onBackButtonClick: () -> Unit = { },
    onCreateVideoClick: () -> Unit = { }
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onVideoSelected(it)
            onVideoSizeChange(it?.let(context::getVideoSize))
        }
    )

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onVideoImageSelected(it)
            onVideoImageSizeChange(it?.let(context::getImageSize))
        }
    )

    Scaffold(
        topBar = {
            CreateVideoScreenTopBar(
                state = state,
                isCreateVideoButtonEnabled = isCreateVideoButtonEnabled,
                onBackButtonClick = onBackButtonClick,
                onCreateVideoClick = onCreateVideoClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(24.dp)
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            VideoTitleTextField(
                videoTitle = videoTitle,
                onVideoTitleChange = onVideoTitleChange
            )

            VideoDescriptionTextField(
                videoDescription = videoDescription,
                onVideoDescriptionChange = onVideoDescriptionChange
            )

            SelectVideoButton(
                onClick = {
                    val input = PickVisualMediaRequest(VideoOnly)
                    videoPickerLauncher.launch(input)
                }
            )

            Video(
                videoUri = videoUri,
                videoSize = videoSize
            )

            SelectVideoImageButton(
                onClick = {
                    val input = PickVisualMediaRequest(ImageOnly)
                    imagePickerLauncher.launch(input)
                }
            )

            VideoImage(
                imageUri = videoImageUri,
                imageSize = videoImageSize
            )
        }
    }
}

@Composable
private fun VideoDescriptionTextField(
    videoDescription: String,
    onVideoDescriptionChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = videoDescription,
        onValueChange = onVideoDescriptionChange,
        textStyle = MaterialTheme.typography.bodySmall,
        shape = MaterialTheme.shapes.small,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done
        ),
        minLines = 2,
        placeholder = {
            Text(
                text = "Description",
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}

@Composable
private fun VideoTitleTextField(
    videoTitle: String,
    onVideoTitleChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = videoTitle,
        onValueChange = onVideoTitleChange,
        textStyle = MaterialTheme.typography.bodySmall,
        shape = MaterialTheme.shapes.small,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Next
        ),
        placeholder = {
            Text(
                text = "Title",
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}

@Composable
private fun SelectVideoButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .heightIn(48.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        ),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = Icons.Outlined.Videocam,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "Select video")
    }
}

@Composable
private fun Video(videoUri: Uri?, videoSize: Size?) {
    val video = videoUri ?: return
    val size = videoSize ?: return
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(0.2f)
        )
    ) {
        Column {
            AndroidView(
                modifier = Modifier
                    .aspectRatio(ratio = size.width.toFloat() / size.height.toFloat())
                    .fillMaxWidth(),
                factory = {
                    VideoView(it)
                },
                update = {
                    it.setVideoURI(video)
                    it.start()
                }
            )

            Text(
                modifier = Modifier.padding(16.dp),
                text = "${size.width} x ${size.height}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun SelectVideoImageButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .heightIn(48.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        ),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = Icons.Outlined.Photo,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "Select image")
    }
}

@Composable
private fun VideoImage(imageUri: Uri?, imageSize: Size?) {
    val image = imageUri ?: return
    val size = imageSize ?: return
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(0.2f)
        )
    ) {
        Column {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.padding(16.dp),
                text = "${size.width} x ${size.height}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun CreateVideoScreenTopBar(
    state: CreateVideoState,
    isCreateVideoButtonEnabled: Boolean,
    onBackButtonClick: () -> Unit,
    onCreateVideoClick: () -> Unit
) {
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
                OutlinedIconButton(
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                    ),
                    onClick = onBackButtonClick
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(
                        visible = state is CreateVideoState.Wait
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Button(
                        enabled = isCreateVideoButtonEnabled,
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
                        ),
                        onClick = onCreateVideoClick
                    ) {
                        Text(text = "Create")
                    }
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )
    }
}
