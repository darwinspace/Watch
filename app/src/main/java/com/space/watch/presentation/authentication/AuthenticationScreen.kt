package com.space.watch.presentation.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.space.watch.R
import com.space.watch.presentation.component.WatchTopBarNameText

@Composable
fun AuthenticationScreen(
    authenticationViewModel: AuthenticationViewModel = viewModel(),
    navController: NavHostController
) {
    AuthenticationScreenContent(
        onSignInClick = {

        }
    )
}

@Composable
private fun AuthenticationScreenContent(onSignInClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WatchTopBarNameText(
                text = stringResource(id = R.string.application_name),
                textStyle = MaterialTheme.typography.displaySmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            SignInButton(onClick = onSignInClick)
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
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
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