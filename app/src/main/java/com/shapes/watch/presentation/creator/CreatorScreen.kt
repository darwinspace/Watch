package com.shapes.watch.presentation.creator

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shapes.watch.R
import com.shapes.watch.presentation.ui.WatchDescription
import com.shapes.watch.presentation.ui.WatchIconButton
import com.shapes.watch.ui.theme.onSurfaceCarbon

@ExperimentalMaterialApi
@Composable
fun CreatorScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Scaffold {
        CreatorScreenContent(scrollState)

        Box(modifier = Modifier.padding(24.dp)) {
            WatchIconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    }
}

@Composable
private fun CreatorScreenContent(scrollState: ScrollState) {
    Surface(modifier = Modifier.verticalScroll(scrollState)) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CreatorData()

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Darwin Delgado",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
            Text(text = "1 Video", style = MaterialTheme.typography.subtitle2)

            Spacer(modifier = Modifier.height(24.dp))

            WatchDescription(text = "Looking back")
        }
    }
}

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
                .background(Color(0xFFE1BEE7))
                .aspectRatio(ratio = 16f / 9f)
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

//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun CreatorScreenPreview() {
//    WatchTheme {
//        CreatorScreen(navController)
//    }
//}
