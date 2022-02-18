package com.shapes.watch.presentation.amor

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shapes.watch.R
import com.shapes.watch.presentation.ui.WatchTopBarNameText

@Composable
fun AmorScreen() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WatchTopBarNameText(stringResource(id = R.string.app_name))

                SignInButton {

                }
            }
        }
    }
}

@Composable
fun SignInButton(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Icon(painter = painterResource(id = R.drawable.icon_google), contentDescription = null)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = "Sign In with Google")
    }
}

@Preview
@Composable
fun SignInButtonPreview() {
    SignInButton {

    }
}
