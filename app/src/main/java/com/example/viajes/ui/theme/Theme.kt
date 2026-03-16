package com.example.viajes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ViajesLightColorScheme = lightColorScheme(
    primary = ViajesPurple800,
    onPrimary = ViajesWhite,
    secondary = ViajesRed,
    onSecondary = ViajesWhite,
    surface = ViajesWhite,
    onSurface = ViajesPurple900,
    background = ViajesGrey,
    onBackground = ViajesPurple900
)

private val ViajesDarkColorScheme = darkColorScheme(
    primary = ViajesPurple200,
    onPrimary = ViajesPurple900,
    secondary = ViajesRed200,
    onSecondary = ViajesDarkBackground,
    surface = ViajesDarkSurface,
    onSurface = ViajesWhite,
    background = ViajesDarkBackground,
    onBackground = ViajesWhite
)

@Composable
fun ViajesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        ViajesDarkColorScheme
    } else {
        ViajesLightColorScheme
    }
    /*val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }*/

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}























/*
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)
*/

