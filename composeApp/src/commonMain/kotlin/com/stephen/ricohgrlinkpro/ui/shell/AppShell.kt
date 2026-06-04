package com.stephen.ricohgrlinkpro.ui.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.AppViewModel
import com.stephen.ricohgrlinkpro.model.SidebarNavItem
import com.stephen.ricohgrlinkpro.ui.components.GrPrimaryButton
import com.stephen.ricohgrlinkpro.ui.components.NavIcon
import com.stephen.ricohgrlinkpro.ui.components.NavIconType
import com.stephen.ricohgrlinkpro.ui.screens.CameraConnectionScreen
import com.stephen.ricohgrlinkpro.ui.screens.FoldersScreen
import com.stephen.ricohgrlinkpro.ui.screens.LocalPhotosScreen
import com.stephen.ricohgrlinkpro.ui.screens.RemoteControlScreen
import com.stephen.ricohgrlinkpro.ui.screens.SettingsScreen

@Composable
fun AppShell(viewModel: AppViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Sidebar(
            selectedItem = viewModel.selectedNavItem,
            onItemSelected = viewModel::selectNavItem,
            modifier = Modifier.fillMaxHeight(),
        )
        Column(modifier = Modifier.weight(1f)) {
            TopToolbar(
                selectedTab = viewModel.selectedTopTab,
                onTabSelected = viewModel::selectTopTab,
                batteryPercent = viewModel.cameraInfo.batteryPercent,
                isCameraConnected = viewModel.isCameraConnected,
                onConnectClick = viewModel::toggleCameraConnection,
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                when (viewModel.selectedNavItem) {
                    SidebarNavItem.CameraConnection -> {
                        if (viewModel.isCameraConnected) {
                            RemoteControlScreen(viewModel = viewModel)
                        } else {
                            CameraConnectionScreen(viewModel = viewModel)
                        }
                    }
                    SidebarNavItem.LocalPhotos -> LocalPhotosScreen(viewModel = viewModel)
                    SidebarNavItem.Folders -> FoldersScreen(viewModel = viewModel)
                    SidebarNavItem.Settings -> SettingsScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Sidebar(
    selectedItem: SidebarNavItem,
    onItemSelected: (SidebarNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(260.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(vertical = 20.dp),
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "GR Link",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "Professional Companion",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(top = 2.dp),
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        SidebarNavItem.entries.forEach { item ->
            SidebarNavRow(
                item = item,
                isSelected = item == selectedItem,
                onClick = { onItemSelected(item) },
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        GrPrimaryButton(
            text = "Import Photos",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            SidebarFooterLink(
                label = "Support",
                icon = NavIconType.Support,
                onClick = {},
            )
            SidebarFooterLink(
                label = "About",
                icon = NavIconType.About,
                onClick = {},
            )
        }
    }
}

@Composable
private fun SidebarNavRow(
    item: SidebarNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val icon = when (item) {
        SidebarNavItem.CameraConnection -> NavIconType.Camera
        SidebarNavItem.LocalPhotos -> NavIconType.Photos
        SidebarNavItem.Folders -> NavIconType.Folder
        SidebarNavItem.Settings -> NavIconType.Settings
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.surfaceContainerHigh
                } else {
                    MaterialTheme.colorScheme.surfaceContainerLow
                },
            )
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(24.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
            )
        } else {
            Spacer(modifier = Modifier.width(2.dp))
        }
        NavIcon(
            item = icon,
            modifier = Modifier.padding(start = 18.dp),
            tint = if (isSelected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            },
        )
        Text(
            text = item.label,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
            },
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

@Composable
private fun SidebarFooterLink(
    label: String,
    icon: NavIconType,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NavIcon(item = icon, size = 16.dp, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
            modifier = Modifier.padding(start = 6.dp),
        )
    }
}
