package com.shapes.watch.presentation.principal

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.common.api.ApiException
import com.shapes.watch.R
import com.shapes.watch.presentation.home.GoogleAuthenticationContract
import com.shapes.watch.presentation.navigation.Screen
import com.shapes.watch.presentation.component.WatchTopBarNameText

@Composable
fun DefaultScreen(navController: NavHostController) {
//    val scope = rememberCoroutineScope()

    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DefaultScreenContent(navController)
        }
    }
}

@Composable
private fun DefaultScreenContent(navController: NavHostController) {
    val signInRequestCode = 1
    val signInResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleAuthenticationContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account != null) {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.DefaultScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    val tag = "DefaultScreenContent"
                    Log.d(tag, "Account was null.")
                }
            } catch (e: Exception) {
                throw e
            }
        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WatchTopBarNameText(
            stringResource(id = R.string.app_name),
            MaterialTheme.typography.h4
        )

        Spacer(modifier = Modifier.height(24.dp))

        SignInButton(
            onClick = {
                signInResultLauncher.launch(signInRequestCode)
//                        signIn()
            }
        )
    }
}

//fun signIn() {
//
//}


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

//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun DefaultScreenPreview() {
//    WatchTheme {
//        val navController = rememberNavController()
//        DefaultScreen(navController)
//    }
//}
