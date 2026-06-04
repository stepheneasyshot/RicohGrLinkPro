package com.stephen.ricohgrlinkpro.ui.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.stephen.ricohgrlinkpro.model.TopTab
import com.stephen.ricohgrlinkpro.ui.components.GrPrimaryButton
import com.stephen.ricohgrlinkpro.ui.components.GrSecondaryButton
import com.stephen.ricohgrlinkpro.ui.components.AvatarPlaceholder
import com.stephen.ricohgrlinkpro.ui.components.NavIcon
import com.stephen.ricohgrlinkpro.ui.components.NavIconType

@Composable
fun TopToolbar(
    selectedTab: TopTab,
    onTabSelected: (TopTab) -> Unit,
    batteryPercent: Int,
    isCameraConnected: Boolean,
    onConnectClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.7f))
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TopTab.entries.forEach { tab ->
            TopTabItem(
                label = tab.label,
                isSelected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NavIcon(item = NavIconType.Sync, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                NavIcon(item = NavIconType.Battery, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                Text(
                    text = "$batteryPercent%",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
            NavIcon(item = NavIconType.SdCard, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))

            if (isCameraConnected) {
                GrSecondaryButton(
                    text = "Disconnect",
                    onClick = onConnectClick,
                )
            } else {
                GrPrimaryButton(
                    text = "Connect Camera",
                    onClick = onConnectClick,
                )
            }

            AvatarPlaceholder()
        }
    }
}

@Composable
private fun TopTabItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            },
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(32.dp)
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
            )
        }
    }
}
