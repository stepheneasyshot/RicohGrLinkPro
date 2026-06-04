package com.stephen.ricohgrlinkpro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GrIcon(
    symbol: String,
    modifier: Modifier = Modifier,
    size: Dp = 18.dp,
    tint: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = symbol,
            style = MaterialTheme.typography.labelMedium,
            color = tint,
        )
    }
}

@Composable
fun NavIcon(
    item: NavIconType,
    modifier: Modifier = Modifier,
    size: Dp = 18.dp,
    tint: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
) {
    GrIcon(symbol = item.symbol, modifier = modifier, size = size, tint = tint)
}

enum class NavIconType(val symbol: String) {
    Camera("⌁"),
    Photos("▣"),
    Folder("▤"),
    Settings("⚙"),
    Support("?"),
    About("i"),
    Sync("↻"),
    Battery("▮"),
    SdCard("▭"),
    Filter("☰"),
    Wifi("⌇"),
    General("◈"),
    Cloud("☁"),
    Info("◎"),
}

@Composable
fun StatusDot(
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color),
    )
}

@Composable
fun AvatarPlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceContainerHighest),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "GR",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        )
    }
}
