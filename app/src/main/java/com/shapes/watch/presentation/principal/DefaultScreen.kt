package com.shapes.watch.presentation.principal

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shapes.watch.R
import com.shapes.watch.presentation.GoogleAuthenticationContract
import com.shapes.watch.presentation.ui.WatchTopBarNameText
import com.shapes.watch.ui.theme.WatchTheme

@Composable
fun DefaultScreen() {
    rememberLauncherForActivityResult(contract = GoogleAuthenticationContract()) {

    }

    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WatchTopBarNameText(
                    stringResource(id = R.string.app_name),
                    MaterialTheme.typography.h4
                )

                Spacer(modifier = Modifier.height(24.dp))

                SignInButton(onClick = { /* TODO */ })
            }
        }
    }
}

@Composable
fun SignInButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 12.dp,
            end = 16.dp,
            bottom = 12.dp
        ),
        colors = outlinedButtonColors(contentColor = MaterialTheme.colors.onSurface)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_google),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = "Sign In with Google")
    }
}


@Preview(showBackground = true, heightDp = 640, widthDp = 360)
@Composable
fun DefaultScreenPreview() {
    WatchTheme {
        DefaultScreen()
    }
}

//@Preview
//@Composable
//fun SignInButtonPreview() {
//    WatchTheme {
//        SignInButton(onClick = {})
//    }
//}
