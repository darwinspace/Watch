package com.shapes.watch.presentation.principal.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shapes.watch.R
import com.shapes.watch.presentation.authentication.component.AuthenticationScreen

@Composable
fun DefaultScreen(navController: NavHostController) {
    Scaffold {
        DefaultScreenContent(navController)
    }
}

@Composable
private fun DefaultScreenContent(navController: NavHostController) {
    AuthenticationScreen(
        navController = navController
    )
}

//@Preview(showBackground = true, heightDp = 640, widthDp = 360)
//@Composable
//fun DefaultScreenPreview() {
//    WatchTheme {
//        val navController = rememberNavController()
//        DefaultScreen(navController)
//    }
//}
