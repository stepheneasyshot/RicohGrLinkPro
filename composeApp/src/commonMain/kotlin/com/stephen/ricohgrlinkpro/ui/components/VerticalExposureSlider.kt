package com.stephen.ricohgrlinkpro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun VerticalExposureSlider(
    label: String,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    valueLabel: String,
    topLabel: String,
    bottomLabel: String,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.width(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        )
        Text(
            text = topLabel,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
        )
        VerticalSliderTrack(
            value = value,
            valueRange = valueRange,
            onValueChange = onValueChange,
            modifier = Modifier
                .height(160.dp)
                .fillMaxHeight(),
        )
        Text(
            text = bottomLabel,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            modifier = Modifier.padding(top = 8.dp),
        )
        Text(
            text = valueLabel,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}

@Composable
fun VerticalIsoSlider(
    value: Int,
    valueRange: IntRange,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.width(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "ISO",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        )
        Text(
            text = "12.8k",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
        )
        VerticalSliderTrack(
            value = value.toFloat(),
            valueRange = valueRange.first.toFloat()..valueRange.last.toFloat(),
            onValueChange = { onValueChange(it.roundToInt()) },
            modifier = Modifier.height(160.dp),
            inverted = true,
        )
        Text(
            text = "100",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            modifier = Modifier.padding(top = 8.dp),
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}

@Composable
private fun VerticalSliderTrack(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    inverted: Boolean = false,
) {
    val normalized = ((value - valueRange.start) / (valueRange.endInclusive - valueRange.start))
        .coerceIn(0f, 1f)
    val displayFraction = if (inverted) 1f - normalized else normalized

    var dragFraction by remember(value) { mutableFloatStateOf(displayFraction) }

    Box(
        modifier = modifier
            .width(24.dp)
            .pointerInput(valueRange) {
                detectDragGestures { change, _ ->
                    change.consume()
                    val y = change.position.y.coerceIn(0f, size.height.toFloat())
                    val fraction = 1f - (y / size.height)
                    dragFraction = fraction.coerceIn(0f, 1f)
                    val newValue = valueRange.start + dragFraction * (valueRange.endInclusive - valueRange.start)
                    onValueChange(newValue)
                }
            },
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(1.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHighest),
        )
        Box(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight(fraction = dragFraction.coerceAtLeast(0.01f))
                .clip(RoundedCornerShape(1.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .align(Alignment.BottomCenter),
        )
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        0,
                        ((1f - dragFraction) * 160.dp.roundToPx() - 8.dp.roundToPx()).roundToInt(),
                    )
                }
                .size(16.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(2.dp, MaterialTheme.colorScheme.onPrimary, CircleShape),
        )
    }
}
