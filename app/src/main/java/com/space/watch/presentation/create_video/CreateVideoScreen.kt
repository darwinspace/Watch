package com.space.watch.presentation.create_video

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.VideoOnly
import androidx.annotation.OptIn
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.space.watch.domain.model.Image
import com.space.watch.domain.model.Video
import com.space.watch.extension.toImage
import com.space.watch.extension.toVideo
import com.space.watch.presentation.`interface`.theme.WatchTheme

@Preview
@Composable
fun CreateVideoScreenPreview() {
    WatchTheme {
        CreateVideoScreen()
    }
}

@Composable
fun CreateVideoScreen(
    videoTitle: () -> String = { String() },
    onVideoTitleChange: (String) -> Unit = { },
    videoDescription: () -> String = { String() },
    onVideoDescriptionChange: (String) -> Unit = { },
    video: () -> Video? = { null },
    onVideoChange: (Video?) -> Unit = { },
    videoImage: () -> Image? = { null },
    onVideoImageChange: (Image?) -> Unit = { },
    isCreateVideoButtonEnabled: () -> Boolean = { false },
    isVideoUploading: () -> Boolean = { false },
    onBackButtonClick: () -> Unit = { },
    onCreateVideoClick: () -> Unit = { }
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onVideoChange(it.toVideo(context))
        }
    )

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onVideoImageChange(it.toImage(context))
        }
    )

    Scaffold(
        topBar = {
            CreateVideoScreenTopBar(
                isCreateVideoButtonEnabled = isCreateVideoButtonEnabled,
                isVideoUploading = isVideoUploading,
                onBackButtonClick = onBackButtonClick,
                onCreateVideoClick = onCreateVideoClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(24.dp)
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

            val videoData = video()
            AnimatedVisibility(visible = videoData != null) {
                SelectedVideo(
                    video = videoData!!
                )
            }

            SelectVideoImageButton(
                onClick = {
                    val input = PickVisualMediaRequest(ImageOnly)
                    imagePickerLauncher.launch(input)
                }
            )

            val imageData = videoImage()
            AnimatedVisibility(visible = imageData != null) {
                SelectedVideoImage(
                    image = imageData!!
                )
            }
        }
    }
}

@Composable
private fun CreateVideoScreenTopBar(
    isCreateVideoButtonEnabled: () -> Boolean,
    isVideoUploading: () -> Boolean,
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
                        visible = isVideoUploading()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Button(
                        enabled = isCreateVideoButtonEnabled(),
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

@Composable
private fun VideoTitleTextField(
    videoTitle: () -> String,
    onVideoTitleChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = videoTitle(),
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
private fun VideoDescriptionTextField(
    videoDescription: () -> String,
    onVideoDescriptionChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = videoDescription(),
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

@OptIn(UnstableApi::class)
@Composable
private fun SelectedVideo(video: Video) {
    val context = LocalContext.current
    val mediaItem = remember(video.content) { MediaItem.fromUri(video.content) }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            prepare()
        }
    }

    var lifecycleEvent by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

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
                    .aspectRatio(ratio = video.size.width.toFloat() / video.size.height.toFloat())
                    .fillMaxWidth(),
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                        setShowRewindButton(false)
                        setShowFastForwardButton(false)
                    }
                },
                update = {
                    when (lifecycleEvent) {
                        Lifecycle.Event.ON_RESUME -> {
                            it.onResume()
                        }

                        Lifecycle.Event.ON_PAUSE -> {
                            it.onPause()
                            it.player?.pause()
                        }

                        else -> Unit
                    }
                }
            )

            Text(
                modifier = Modifier.padding(16.dp),
                text = "${video.size.width} x ${video.size.height}",
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
private fun SelectedVideoImage(image: Image) {
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
                model = image.content,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.padding(16.dp),
                text = "${image.size.width} x ${image.size.height}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
