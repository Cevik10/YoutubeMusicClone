package com.hakancevik.youtubemusicclone.common.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = almost_black,                      // Use full_red for the primary color
    onPrimary = white,                       // Use white for text/icons on the primary color
    primaryContainer = black,         // Use almost_black for the primary container color
    onPrimaryContainer = white,              // Use white for text/icons on the primary container color
    secondary = gray_300,                   // Use text_gray for the secondary color
    onSecondary = white,                     // Use white for text/icons on the secondary color
    secondaryContainer = gray_300, // Use digital_background_gray for the secondary container color
    onSecondaryContainer = almost_black,     // Use almost_black for text/icons on the secondary container color
    tertiary = digital_background_gray,      // Use digital_background_gray for the tertiary color
    onTertiary = almost_black,               // Use almost_black for text/icons on the tertiary color
    tertiaryContainer = text_gray,           // Use text_gray for the tertiary container color
    onTertiaryContainer = white,             // Use white for text/icons on the tertiary container color
    error = full_red,                        // Use full_red for the error color
    errorContainer = text_gray,              // Use text_gray for the error container color
    onError = white,                         // Use white for text/icons on the error color
    onErrorContainer = almost_black,         // Use almost_black for text/icons on the error container color
    background = almost_black,               // Use almost_black for the background color
    onBackground = white,                    // Use white for text/icons on the background color
    surface = almost_black,                  // Use almost_black for the surface color
    onSurface = white,                       // Use white for text/icons on the surface color
    surfaceVariant = digital_background_gray, // Use digital_background_gray for the surface variant color
    onSurfaceVariant = almost_black,         // Use almost_black for text/icons on the surface variant color
    outline = text_gray,                     // Use text_gray for outlines
    inverseOnSurface = white,         // Use almost_black for inverse text/icons on surfaces
    inverseSurface = white,                  // Use white for the inverse surface color
    inversePrimary = digital_background_gray, // Use digital_background_gray for the inverse primary color
    surfaceTint = full_red,                  // Use full_red for the surface tint color
    outlineVariant = text_gray,              // Use text_gray for outline variants
    scrim = black                            // Use black for the scrim color
)

private val DarkColors = darkColorScheme(
    primary = almost_black,                      // Use full_red for the primary color
    onPrimary = white,                       // Use white for text/icons on the primary color
    primaryContainer = black,         // Use almost_black for the primary container color
    onPrimaryContainer = white,              // Use white for text/icons on the primary container color
    secondary = gray_300,                   // Use text_gray for the secondary color
    onSecondary = white,                     // Use white for text/icons on the secondary color
    secondaryContainer = gray_300, // Use digital_background_gray for the secondary container color
    onSecondaryContainer = almost_black,     // Use almost_black for text/icons on the secondary container color
    tertiary = digital_background_gray,      // Use digital_background_gray for the tertiary color
    onTertiary = almost_black,               // Use almost_black for text/icons on the tertiary color
    tertiaryContainer = text_gray,           // Use text_gray for the tertiary container color
    onTertiaryContainer = white,             // Use white for text/icons on the tertiary container color
    error = full_red,                        // Use full_red for the error color
    errorContainer = text_gray,              // Use text_gray for the error container color
    onError = white,                         // Use white for text/icons on the error color
    onErrorContainer = almost_black,         // Use almost_black for text/icons on the error container color
    background = almost_black,               // Use almost_black for the background color
    onBackground = white,                    // Use white for text/icons on the background color
    surface = almost_black,                  // Use almost_black for the surface color
    onSurface = white,                       // Use white for text/icons on the surface color
    surfaceVariant = digital_background_gray, // Use digital_background_gray for the surface variant color
    onSurfaceVariant = almost_black,         // Use almost_black for text/icons on the surface variant color
    outline = text_gray,                     // Use text_gray for outlines
    inverseOnSurface = white,         // Use almost_black for inverse text/icons on surfaces
    inverseSurface = white,                  // Use white for the inverse surface color
    inversePrimary = digital_background_gray, // Use digital_background_gray for the inverse primary color
    surfaceTint = full_red,                  // Use full_red for the surface tint color
    outlineVariant = text_gray,              // Use text_gray for outline variants
    scrim = black                           // Use black for the scrim color
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) {
        DarkColors
    } else {
        LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
