package com.stephen.ricohgrlinkpro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.theme.GlassBorder

@Composable
fun GlassSurface(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    backgroundAlpha: Float = 0.85f,
    content: @Composable BoxScope.() -> Unit,
) {
    val shape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .clip(shape)
            .background(
                MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = backgroundAlpha),
            )
            .border(
                width = 0.5.dp,
                color = GlassBorder,
                shape = shape,
            ),
        content = content,
    )
}

@Composable
fun GlassPanel(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    GlassSurface(
        modifier = modifier,
        cornerRadius = 0.dp,
        backgroundAlpha = 0.7f,
        content = content,
    )
}
