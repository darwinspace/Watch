package com.shapes.watch.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shapes.watch.presentation.component.WatchButton
import com.shapes.watch.presentation.component.WatchIconButton
import com.shapes.watch.presentation.component.WatchTextField
import com.shapes.watch.presentation.component.WatchTopBar
import com.shapes.watch.ui.theme.onSurfaceCarbon

@ExperimentalMaterialApi
@Composable
fun ProfileScreen(navController: NavHostController) {
    var saveButtonEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                ProfileScreenTopBar(
                    saveButtonEnabled = saveButtonEnabled,
                    onSaveClick = { /*TODO*/ },
                    onCloseClick = { navController.popBackStack() }
                )

                Divider()
            }
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileData()

                Spacer(modifier = Modifier.height(24.dp))

                var text by remember { mutableStateOf(String()) }
                WatchTextField(value = text, onValueChange = { text = it })
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun ProfileScreenTopBar(
    saveButtonEnabled: Boolean = false,
    onSaveClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    WatchTopBar(text = "Profile") {
        WatchButton(
            backgroundColor = MaterialTheme.colors.primary,
            onClick = onSaveClick,
            enabled = saveButtonEnabled
        ) {
            Text(text = "Save")
            Spacer(modifier = Modifier.width(12.dp))
            Icon(imageVector = Icons.Default.Done, contentDescription = null)
        }

        Spacer(modifier = Modifier.width(12.dp))

        WatchIconButton(onClick = onCloseClick) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun ProfileData() {
    Box(contentAlignment = Alignment.BottomCenter) {
        ProfileCover(onEditClick = { /* TODO */ })

        ProfileImage(onEditClick = { /* TODO */ })
    }
}

@ExperimentalMaterialApi
@Composable
private fun ProfileCover(onEditClick: () -> Unit) {
    Box(contentAlignment = Alignment.TopEnd) {
        Box(
            modifier = Modifier
                .padding(bottom = 76.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurfaceCarbon,
                    shape = MaterialTheme.shapes.medium
                )
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.onSurfaceCarbon)
                .aspectRatio(ratio = 16f / 9f)
                .fillMaxWidth()
        )

        Box(modifier = Modifier.padding(24.dp)) {
            WatchIconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun ProfileImage(onEditClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .border(
                    width = 8.dp,
                    color = MaterialTheme.colors.surface,
                    shape = CircleShape
                )
                .padding(8.dp)
                .clip(CircleShape)
                .background(Color.Blue)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurfaceCarbon,
                    shape = CircleShape
                )
                .size(96.dp)
        )

//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_background),
//            contentDescription = null,
//            modifier = Modifier
//
//        )

        WatchIconButton(onClick = onEditClick) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}

//@ExperimentalMaterialApi
//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun ProfileScreenPreview() {
//    WatchTheme {
//        ProfileScreen()
//    }
//}
