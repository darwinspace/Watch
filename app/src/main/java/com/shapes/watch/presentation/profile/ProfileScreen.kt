package com.shapes.watch.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shapes.watch.R
import com.shapes.watch.presentation.ui.WatchIconButton
import com.shapes.watch.presentation.ui.WatchTextField
import com.shapes.watch.presentation.ui.WatchTopBar
import com.shapes.watch.ui.theme.onSurfaceCarbon

@ExperimentalMaterialApi
@Composable
fun ProfileScreen() {
    val saveButtonEnabled = false

    Scaffold(
        topBar = {
            Column {
                WatchTopBar(text = "Profile") {
                    Button(
                        onClick = { /* TODO */ },
                        enabled = saveButtonEnabled,
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
                    ) {
                        Text(text = "Save")
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
                    }
                }

                Divider()
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
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
private fun ProfileData() {
    Box(contentAlignment = Alignment.BottomCenter) {
        ProfileCover { /* TODO */ }

        ProfileImage { /* TODO */ }
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
                .background(Color(0xFFE1BEE7))
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

        WatchIconButton(onClick = onEditClick) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}


//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun ProfileScreenPreview() {
//    WatchTheme {
//        ProfileScreen()
//    }
//}
