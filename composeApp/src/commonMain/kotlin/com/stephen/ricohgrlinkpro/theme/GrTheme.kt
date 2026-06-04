package com.stephen.ricohgrlinkpro.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ObsidianAmberColorScheme = darkColorScheme(
    primary = AmberPrimary,
    onPrimary = AmberOnPrimary,
    primaryContainer = AmberPrimaryContainer,
    onPrimaryContainer = AmberOnPrimaryContainer,
    secondary = AmberSecondary,
    onSecondary = AmberOnSecondary,
    secondaryContainer = AmberSecondaryContainer,
    onSecondaryContainer = AmberOnSecondaryContainer,
    tertiary = AmberSecondary,
    onTertiary = AmberOnSecondary,
    background = ObsidianBackground,
    onBackground = ObsidianOnSurface,
    surface = ObsidianBackground,
    onSurface = ObsidianOnSurface,
    surfaceVariant = ObsidianSurfaceContainerHighest,
    onSurfaceVariant = ObsidianOnSurfaceVariant,
    outline = ObsidianOutline,
    outlineVariant = ObsidianOutlineVariant,
    surfaceDim = ObsidianSurfaceDim,
    surfaceBright = ObsidianSurfaceBright,
    surfaceContainerLowest = ObsidianSurfaceContainerLowest,
    surfaceContainerLow = ObsidianSurfaceContainerLow,
    surfaceContainer = ObsidianSurfaceContainer,
    surfaceContainerHigh = ObsidianSurfaceContainerHigh,
    surfaceContainerHighest = ObsidianSurfaceContainerHighest,
    error = AmberError,
    onError = AmberOnError,
    errorContainer = AmberErrorContainer,
    onErrorContainer = AmberOnErrorContainer,
)

@Composable
fun GrTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ObsidianAmberColorScheme,
        typography = GrTypography,
        shapes = GrShapes,
        content = content,
    )
}
