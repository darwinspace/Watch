package com.shapes.watch.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shapes.watch.R

private val SpaceGrotesk = FontFamily(
    Font(R.font.space_grotesk_light, FontWeight.Light),
    Font(R.font.space_grotesk_regular, FontWeight.Normal),
    Font(R.font.space_grotesk_medium, FontWeight.Medium),
    Font(R.font.space_grotesk_semibold, FontWeight.SemiBold),
    Font(R.font.space_grotesk_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 100.sp
    ),
    h2 = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp
    ),
    h3 = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 52.sp
    ),
    h4 = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    h5 = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontSize = 20.sp,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
    ),
    subtitle1 = TextStyle(
        fontSize = 18.sp,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
    ),
    subtitle2 = TextStyle(
        fontSize = 16.sp,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Medium,
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Medium,
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Medium,
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Medium
    )
)