package com.stephen.ricohgrlinkpro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.AppViewModel
import com.stephen.ricohgrlinkpro.model.ConnectionStatus
import com.stephen.ricohgrlinkpro.ui.components.GlassSurface
import com.stephen.ricohgrlinkpro.ui.components.GrToggle
import com.stephen.ricohgrlinkpro.ui.components.InitializeLinkButton
import com.stephen.ricohgrlinkpro.ui.components.NavIcon
import com.stephen.ricohgrlinkpro.ui.components.NavIconType
import com.stephen.ricohgrlinkpro.ui.components.StatusBadge
import com.stephen.ricohgrlinkpro.ui.components.StatusDot

@Composable
fun CameraConnectionScreen(viewModel: AppViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF1A1510),
                            Color(0xFF0E0E0E),
                            Color(0xFF131313),
                        ),
                    ),
                ),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            ) {
                DeviceStatusCard(
                    status = viewModel.connectionStatus,
                    model = viewModel.cameraInfo.model,
                    serial = viewModel.cameraInfo.serial,
                    progress = viewModel.pairingProgress,
                    modifier = Modifier.width(320.dp),
                )
                ConnectionSettingsCard(
                    autoSync = viewModel.settings.autoSyncLibrary,
                    onAutoSyncChange = { checked ->
                        viewModel.updateSettings { copy(autoSyncLibrary = checked) }
                    },
                    modifier = Modifier.width(320.dp),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 48.dp),
            ) {
                Text(
                    text = "Ready for Precision.",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "Connect your Ricoh GR to access instant wireless transfers, remote shutter\ncontrol, and advanced lens settings directly from your workstation.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 24.dp)
                        .width(520.dp),
                )
                InitializeLinkButton(onClick = viewModel::initializeLink)
                Text(
                    text = "INITIALIZE LINK",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(top = 12.dp),
                )
            }
        }
    }
}

@Composable
private fun DeviceStatusCard(
    status: ConnectionStatus,
    model: String,
    serial: String,
    progress: Float,
    modifier: Modifier = Modifier,
) {
    GlassSurface(modifier = modifier) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "DEVICE STATUS",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 12.dp),
            ) {
                StatusDot()
                Text(
                    text = when (status) {
                        ConnectionStatus.Pairing -> "Pairing"
                        ConnectionStatus.Connected -> "Connected"
                        ConnectionStatus.Disconnected -> "Disconnected"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
            Text(
                text = model,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp),
            )
            Text(
                text = "Serial: $serial",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(top = 4.dp),
            )
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(3.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            )
            Text(
                text = "ESTABLISHING WIFI LINK...",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

@Composable
private fun ConnectionSettingsCard(
    autoSync: Boolean,
    onAutoSyncChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    GlassSurface(modifier = modifier) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                NavIcon(item = NavIconType.Wifi)
                Text(
                    text = "Connection Settings",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
            ConnectionSettingRow(label = "Bluetooth LE", badge = "AUTO")
            ConnectionSettingRow(label = "Wi-Fi (5GHz)", badge = "ENABLED")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Auto-Sync Library",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                )
                GrToggle(checked = autoSync, onCheckedChange = onAutoSyncChange)
            }
        }
    }
}

@Composable
private fun ConnectionSettingRow(
    label: String,
    badge: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
        )
        StatusBadge(text = badge)
    }
}
