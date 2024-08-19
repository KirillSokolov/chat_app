package com.test.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.test.design.theme.typography

// https://developer.android.com/jetpack/compose/designsystems/anatomy

private val darkColorScheme = darkColorScheme(
    primary = VioletBlue,
    secondary = EldenRingOrange,
    onSecondary = White,
    inversePrimary = CadmiumYellow,
    tertiary = GreenBellPepper,
    primaryContainer = SagatPurple,
    error = Firebrick,
    background = White,
    surface = BrightGrey,
    onSurface = Adhesion,
    onBackground = Raven
)

private val lightColorScheme = lightColorScheme(
    primary = VioletBlue,
    secondary = EldenRingOrange,
    onSecondary = White,
    inversePrimary = CadmiumYellow,
    tertiary = GreenBellPepper,
    primaryContainer = SagatPurple,
    error = Firebrick,
    background = White,
    surface = BrightGrey,
    onSurface = Adhesion,
    onBackground = Raven
)

@Composable
fun ChatAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = typography, content = content
    )
}