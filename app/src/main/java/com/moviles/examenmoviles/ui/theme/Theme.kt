package com.moviles.examenmoviles.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    secondary = SecondaryDark,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF005047),
    onSecondaryContainer = Color(0xFFB1EBE0),
    tertiary = Pink80,
    onTertiary = Color.Black,
    error = Color(0xFFCFB8F0),
    onError = Color(0xFF601E48),
    background = Neutral10,
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    outline = Color(0xFF9E9DA1)
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005D),
    secondary = SecondaryLight,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFB1EBE0),
    onSecondaryContainer = Color(0xFF004D44),
    tertiary = Pink40,
    onTertiary = Color.White,
    error = Color(0xFFB3261E),
    onError = Color.White,
    background = Neutral95,
    onBackground = Neutral10,
    surface = Color.White,
    onSurface = Neutral10,
    outline = Color(0xFF79747E)
)

/**
 * Material Design 3 theme for the Coworking Spaces application
 */
@Composable
fun ExamenMovilesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}