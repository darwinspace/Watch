package com.shapes.watch.presentation.create

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
fun CreateScreen(navController: NavHostController) {
    var uploadButtonEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                CreateScreenTopBar(
                    uploadButtonEnabled = uploadButtonEnabled,
                    onUploadClick = {
                        /*TODO*/
                    },
                    onCloseClick = {
                        navController.popBackStack()
                    }
                )

                Divider()
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            CreateScreenContent()
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun CreateScreenContent() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        CreateVideo()

        Spacer(modifier = Modifier.height(24.dp))

        WatchTextField(value = "Title (required)", onValueChange = {})

        Spacer(modifier = Modifier.height(16.dp))

        WatchTextField(value = "Description\n", onValueChange = {})
    }
}

@ExperimentalMaterialApi
@Composable
private fun CreateVideo() {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurfaceCarbon,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.onSurfaceCarbon)
            .aspectRatio(ratio = 16f / 9f)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        WatchIconButton(onClick = { /* TODO */ }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}
