package com.stephen.ricohgrlinkpro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.AppViewModel
import com.stephen.ricohgrlinkpro.model.LibraryViewMode
import com.stephen.ricohgrlinkpro.ui.components.GrSecondaryButton

@Composable
fun FoldersScreen(viewModel: AppViewModel) {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .width(220.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .padding(16.dp),
        ) {
            Text(
                text = "Folders",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(16.dp))

            viewModel.folders.forEach { folder ->
                FolderRow(
                    name = folder.name,
                    photoCount = folder.photoCount,
                    isSelected = folder.id == viewModel.selectedFolderId,
                    onClick = { viewModel.selectFolder(folder.id) },
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            GrSecondaryButton(
                text = "Add Folder",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        }

        val selectedFolder = viewModel.folders.first { it.id == viewModel.selectedFolderId }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp, vertical = 16.dp),
        ) {
            LibraryHeader(
                title = selectedFolder.name,
                subtitle = selectedFolder.path,
                isGrid = viewModel.libraryViewMode == LibraryViewMode.Grid,
                onViewModeChange = viewModel::setLibraryViewMode,
            )
            Spacer(modifier = Modifier.height(20.dp))
            PhotoGallery(
                photos = viewModel.photos,
                isGrid = viewModel.libraryViewMode == LibraryViewMode.Grid,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun FolderRow(
    name: String,
    photoCount: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.surfaceContainerHigh
                } else {
                    MaterialTheme.colorScheme.surfaceContainerLow
                },
                RoundedCornerShape(6.dp),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            )
            Text(
                text = "$photoCount photos",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
            )
        }
    }
}
