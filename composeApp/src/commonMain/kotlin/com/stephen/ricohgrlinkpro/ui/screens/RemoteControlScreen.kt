package com.stephen.ricohgrlinkpro.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.AppViewModel
import com.stephen.ricohgrlinkpro.data.MockData
import com.stephen.ricohgrlinkpro.theme.LiveRed
import com.stephen.ricohgrlinkpro.ui.components.DropdownField
import com.stephen.ricohgrlinkpro.ui.components.FilmSimGrid
import com.stephen.ricohgrlinkpro.ui.components.ShutterControlsRow
import com.stephen.ricohgrlinkpro.ui.components.StatusBadge
import com.stephen.ricohgrlinkpro.ui.components.StatusDot
import com.stephen.ricohgrlinkpro.ui.components.VerticalExposureSlider
import com.stephen.ricohgrlinkpro.ui.components.VerticalIsoSlider

@Composable
fun RemoteControlScreen(viewModel: AppViewModel) {
    Row(modifier = Modifier.fillMaxSize()) {
        LiveViewPanel(
            settings = viewModel.remoteSettings,
            modifier = Modifier.weight(1f),
        )
        ControlPanel(
            viewModel = viewModel,
            modifier = Modifier
                .width(280.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surfaceContainerLow),
        )
    }
}

@Composable
private fun LiveViewPanel(
    settings: com.stephen.ricohgrlinkpro.model.RemoteSettings,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF1A2838),
                            Color(0xFF2D1F3D),
                            Color(0xFF0E1520),
                        ),
                    ),
                ),
        )

        GridOverlay(modifier = Modifier.fillMaxSize())

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StatusDot(color = LiveRed)
            Text(
                text = "LIVE 4K",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 6.dp),
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            StatusBadge(text = "RAW+L")
            StatusBadge(text = "AF-S")
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black.copy(alpha = 0.55f))
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            ExposureLabel(settings.aperture)
            ExposureLabel(settings.shutterSpeed)
            ExposureLabel("ISO ${settings.iso}")
            ExposureLabel(settings.ev)
        }
    }
}

@Composable
private fun ExposureLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = Color.White.copy(alpha = 0.9f),
        fontWeight = FontWeight.Medium,
    )
}

@Composable
private fun GridOverlay(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val lineColor = Color.White.copy(alpha = 0.12f)
        val stroke = 0.5f
        drawLine(lineColor, Offset(size.width / 3f, 0f), Offset(size.width / 3f, size.height), stroke)
        drawLine(lineColor, Offset(2f * size.width / 3f, 0f), Offset(2f * size.width / 3f, size.height), stroke)
        drawLine(lineColor, Offset(0f, size.height / 3f), Offset(size.width, size.height / 3f), stroke)
        drawLine(lineColor, Offset(0f, 2f * size.height / 3f), Offset(size.width, 2f * size.height / 3f), stroke)
    }
}

@Composable
private fun ControlPanel(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
) {
    val settings = viewModel.remoteSettings

    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            VerticalExposureSlider(
                label = "EXPOSURE",
                value = settings.exposure,
                valueRange = -3f..3f,
                valueLabel = settings.ev.removePrefix("EV "),
                topLabel = "+3",
                bottomLabel = "-3",
                onValueChange = { value ->
                    val formatted = ((value * 10).toInt() / 10f)
                    val evText = if (formatted >= 0) "+$formatted" else formatted.toString()
                    viewModel.updateRemoteSettings {
                        copy(
                            exposure = value,
                            ev = "EV $evText",
                        )
                    }
                },
            )
            VerticalIsoSlider(
                value = settings.iso,
                valueRange = 100..12800,
                onValueChange = { iso ->
                    viewModel.updateRemoteSettings { copy(iso = iso) }
                },
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        FilmSimGrid(
            simulations = MockData.filmSimulations,
            selected = settings.selectedFilmSim,
            onSelected = { name ->
                viewModel.updateRemoteSettings { copy(selectedFilmSim = name) }
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        DropdownField(label = "WB", value = settings.whiteBalance)
        Spacer(modifier = Modifier.height(12.dp))
        DropdownField(label = "FOCUS", value = settings.focusMode)

        Spacer(modifier = Modifier.weight(1f))

        ShutterControlsRow(onShutterClick = {})
    }
}
