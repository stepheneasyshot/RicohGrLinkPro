package com.stephen.ricohgrlinkpro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.model.PhotoItem

@Composable
fun PhotoGridItem(
    photo: PhotoItem,
    modifier: Modifier = Modifier,
    isListMode: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    if (isListMode) {
        PhotoListRow(photo = photo, isHovered = isHovered, interactionSource = interactionSource, modifier = modifier)
    } else {
        Box(
            modifier = modifier
                .aspectRatio(photo.aspectRatio)
                .clip(RoundedCornerShape(4.dp))
                .hoverable(interactionSource)
                .then(
                    if (isHovered) {
                        Modifier.border(
                            1.dp,
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(4.dp),
                        )
                    } else {
                        Modifier
                    },
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                photo.placeholderColor,
                                photo.placeholderColor.copy(alpha = 0.7f),
                            ),
                        ),
                    ),
            )
            if (isHovered) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                            ),
                        )
                        .padding(8.dp),
                ) {
                    Text(
                        text = "${photo.aperture}  ${photo.shutterSpeed}  ${photo.iso}",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White.copy(alpha = 0.9f),
                    )
                }
            }
        }
    }
}

@Composable
private fun PhotoListRow(
    photo: PhotoItem,
    isHovered: Boolean,
    interactionSource: MutableInteractionSource,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (isHovered) {
                    MaterialTheme.colorScheme.surfaceContainerHigh
                } else {
                    MaterialTheme.colorScheme.surfaceContainerLow
                },
            )
            .hoverable(interactionSource)
            .padding(12.dp),
    ) {
        Column {
            Text(
                text = photo.fileName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "${photo.aperture}  ${photo.shutterSpeed}  ${photo.iso}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            )
        }
    }
}
