package com.shapes.watch.presentation.authentication.component

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.common.api.ApiException
import com.shapes.watch.R
import com.shapes.watch.presentation.component.WatchTopBarNameText
import com.shapes.watch.presentation.home.GoogleAuthenticationContract
import com.shapes.watch.presentation.navigation.Screen

@Composable
fun AuthenticationScreen(
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val user = authenticationViewModel.user.value

    val signInRequestCode = 1
    val signInResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleAuthenticationContract()) { task ->
            Log.d("AuthenticationScreen", "AuthenticationScreen: TASK TRYING.")
            try {
                val account = task?.getResult(ApiException::class.java)!!
                authenticationViewModel.signIn(account.idToken!!)
            } catch (e: Exception) {
                Log.w("AuthenticationScreen", "AuthenticationScreen: FATAL", e)
            }
        }


    AuthenticationScreenContent(
        onSignInClick = {
            signInResultLauncher.launch(signInRequestCode)
        }
    )

    LaunchedEffect(user) {
        user?.let {
            navController.navigate(Screen.HomeScreen.route) {
//                popUpTo(Screen.DefaultScreen.route) {
//                    inclusive = true
//                }
            }
        }
    }
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
                text = stringResource(id = R.string.app_name),
                textStyle = MaterialTheme.typography.h4
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
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.onSurface)
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