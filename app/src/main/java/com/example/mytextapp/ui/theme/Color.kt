package com.example.mytextapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


val pink100 = Color(0xFFFFF1F1)
val pink900 = Color(0xFF3f2c2c)
val gray = Color(0xFF232323)
val white = Color.White
val whit850 = Color.White.copy(alpha = .85f)
val whit150 = Color.White.copy(alpha = .15f)
val green900 = Color(0xFF2d3b2d)
val green300 = Color(0xFFb8c9b8)

var btnBlue = Color(0XFF0061FF)
val blue = Color(0xFF579DFD)
val red = Color(0xFFEB685E)
val green = Color(0xFF27BA6A)
val yellow = Color(0xFFFFCB1F)
val black = Color(0XFF000000)

val orange =Color(0XFFEE5327)




private val LightColorPalette = lightColors(
    primary = pink100,
    primaryVariant = Purple700,
    secondary = pink900,
    background = white,
    surface = whit850,
    onPrimary = gray,
    onSecondary = white,
    onBackground = gray,
    onSurface = gray,
)


private val DarkColorPalette = darkColors(
    primary = green900,
    primaryVariant = Purple700,
    secondary = green300,
    background = gray,
    surface = whit150,
    onPrimary = white,
    onSecondary = gray,
    onBackground = white,
    onSurface = whit850,
)


val Colors.snackbarAction: Color
    get() = if (isLight) Teal200 else Purple700