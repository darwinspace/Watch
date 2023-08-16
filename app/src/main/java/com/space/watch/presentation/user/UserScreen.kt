package com.space.watch.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.space.watch.domain.model.User
import com.space.watch.domain.model.UserContent
import com.space.watch.presentation.component.WatchDescription
import com.space.watch.presentation.home.Video

@Composable
fun UserScreen(
    viewModel: UserViewModel,
    navController: NavHostController,
    user: User
) {
    Scaffold {
        it

        /*UserScreenContent(
            navController = navController,
            userContent = state.userContent,
            user = user
        )*/

        Box(modifier = Modifier.padding(24.dp)) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    }
}


@Composable
private fun UserScreenContent(
    navController: NavHostController,
    userContent: UserContent,
    user: User
) {
    Surface {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserData(user)

                    Spacer(modifier = Modifier.height(12.dp))

                    UserName(user.name)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${userContent.videos.size} Video",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    user.description?.let { description ->
                        WatchDescription(text = description)
                    }
                }
            }

            items(userContent.videos) { video ->
                Video(
                    videoInformation = video,
                    onClick = {
                        /*TODO*/
                    },
                    onUserClick = {
                        /*TODO*/
                    }
                )
            }
        }
    }
}

@Composable
private fun UserName(name: String) {
    Text(
        text = name,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun UserData(user: User) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .padding(bottom = 64.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.medium
                )
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                .aspectRatio(16f / 9f)
        ) {
            Image(
                painter = rememberImagePainter(user.coverUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        Image(
            painter = rememberImagePainter(data = user.photoUrl),
            contentDescription = null,
            modifier = Modifier
                .border(
                    width = 8.dp,
                    color = MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                )
                .padding(8.dp)
                .clip(shape = CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                    shape = CircleShape
                )
                .size(96.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}
