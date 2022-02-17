package com.shapes.watch.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Colors.onSurfaceCarbon: Color
    get() {
        return onSurface.copy(alpha = 0.1f)
    }


private val DarkColorPalette = darkColors(
    primary = PinkA700,
    primaryVariant = PinkA200,
    secondary = Pink500
)

private val LightColorPalette = lightColors(
    primary = PinkA200,
    primaryVariant = PinkA700,
    secondary = Pink500

    /*
        Other default colors to override
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
    */
)

@Composable
fun WatchTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
