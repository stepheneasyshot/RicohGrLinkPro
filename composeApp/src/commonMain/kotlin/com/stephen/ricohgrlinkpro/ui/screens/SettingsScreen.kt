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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.AppViewModel
import com.stephen.ricohgrlinkpro.model.AppearanceMode
import com.stephen.ricohgrlinkpro.ui.components.GrSecondaryButton
import com.stephen.ricohgrlinkpro.ui.components.GrToggle
import com.stephen.ricohgrlinkpro.ui.components.NavIcon
import com.stephen.ricohgrlinkpro.ui.components.NavIconType
import com.stephen.ricohgrlinkpro.ui.components.SegmentedControl
import com.stephen.ricohgrlinkpro.ui.components.SettingsDivider
import com.stephen.ricohgrlinkpro.ui.components.SettingsRow
import com.stephen.ricohgrlinkpro.ui.components.SettingsSection

@Composable
fun SettingsScreen(viewModel: AppViewModel) {
    val settings = viewModel.settings

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp, vertical = 24.dp),
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(28.dp))

        SettingsSection(
            title = "General",
            icon = { NavIcon(item = NavIconType.General, size = 20.dp) },
        ) {
            SettingsRow(
                title = "Launch at Login",
                description = "Start GR Link automatically when you log in.",
                trailing = {
                    GrToggle(
                        checked = settings.launchAtLogin,
                        onCheckedChange = { checked ->
                            viewModel.updateSettings { copy(launchAtLogin = checked) }
                        },
                    )
                },
            )
            SettingsDivider()
            SettingsRow(
                title = "Appearance",
                trailing = {
                    SegmentedControl(
                        options = AppearanceMode.entries,
                        selected = settings.appearance,
                        onSelected = { mode ->
                            viewModel.updateSettings { copy(appearance = mode) }
                        },
                        label = { it.label },
                    )
                },
            )
            SettingsDivider()
            SettingsRow(
                title = "Default Save Path",
                trailing = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = settings.defaultSavePath,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier.padding(end = 12.dp),
                        )
                        GrSecondaryButton(text = "Change...", onClick = {})
                    }
                },
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        SettingsSection(
            title = "Camera Sync",
            icon = { NavIcon(item = NavIconType.Camera, size = 20.dp) },
        ) {
            SettingsRow(
                title = "Auto-Import on Connection",
                description = "Automatically sync new images when Ricoh GR is detected.",
                trailing = {
                    GrToggle(
                        checked = settings.autoImportOnConnection,
                        onCheckedChange = { checked ->
                            viewModel.updateSettings { copy(autoImportOnConnection = checked) }
                        },
                    )
                },
            )
            SettingsDivider()
            SettingsRow(
                title = "Keep Raw Files",
                description = "Transfer both DNG and JPEG files during sync.",
                trailing = {
                    GrToggle(
                        checked = settings.keepRawFiles,
                        onCheckedChange = { checked ->
                            viewModel.updateSettings { copy(keepRawFiles = checked) }
                        },
                    )
                },
            )
            SettingsDivider()
            SettingsRow(
                title = "Apply Lens Profile",
                description = "Apply automatic distortion correction on import.",
                trailing = {
                    GrToggle(
                        checked = settings.applyLensProfile,
                        onCheckedChange = { checked ->
                            viewModel.updateSettings { copy(applyLensProfile = checked) }
                        },
                    )
                },
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        SettingsSection(
            title = "Cloud Backup",
            icon = { NavIcon(item = NavIconType.Cloud, size = 20.dp) },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "GR Cloud Pro",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "Syncing ${settings.cloudPhotosUsed} photos (${settings.cloudStorageUsedGb} GB of ${settings.cloudStorageTotalGb} GB used)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                        modifier = Modifier.padding(top = 4.dp),
                    )
                    LinearProgressIndicator(
                        progress = {
                            settings.cloudStorageUsedGb.toFloat() / settings.cloudStorageTotalGb
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .height(4.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                    )
                }
                GrSecondaryButton(
                    text = "Manage Subscription",
                    onClick = {},
                    modifier = Modifier.padding(start = 16.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        SettingsSection(
            title = "About",
            icon = { NavIcon(item = NavIconType.Info, size = 20.dp) },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(14.dp),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "GR",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                Text(
                    text = "GR Link",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp),
                )
                Text(
                    text = "Version 2.4.1 (Stable)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(top = 4.dp),
                )
                Text(
                    text = "The professional companion toolkit for the Ricoh GR series. Designed for photographers who demand precision, speed, and a distraction-free workflow.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(0.8f),
                )
                Row(
                    modifier = Modifier.padding(top = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    AboutLink("Check for Updates")
                    Text("·", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
                    AboutLink("Privacy Policy")
                    Text("·", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
                    AboutLink("License Agreement")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun AboutLink(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primaryContainer,
        textDecoration = TextDecoration.Underline,
    )
}
