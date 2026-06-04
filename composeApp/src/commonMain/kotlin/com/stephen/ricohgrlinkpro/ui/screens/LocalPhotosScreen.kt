package com.stephen.ricohgrlinkpro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.AppViewModel
import com.stephen.ricohgrlinkpro.data.MockData
import com.stephen.ricohgrlinkpro.model.LibraryViewMode
import com.stephen.ricohgrlinkpro.ui.components.GridListToggle
import com.stephen.ricohgrlinkpro.ui.components.NavIcon
import com.stephen.ricohgrlinkpro.ui.components.NavIconType
import com.stephen.ricohgrlinkpro.ui.components.PhotoGridItem

@Composable
fun LocalPhotosScreen(viewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        LibraryHeader(
            title = MockData.collectionTitle,
            subtitle = MockData.collectionSubtitle,
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

@Composable
fun LibraryHeader(
    title: String,
    subtitle: String,
    isGrid: Boolean,
    onViewModeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(top = 4.dp),
            )
        }
        GridListToggle(isGrid = isGrid, onToggle = onViewModeChange)
        Row(
            modifier = Modifier
                .padding(start = 12.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceContainerHigh,
                    RoundedCornerShape(6.dp),
                )
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NavIcon(item = NavIconType.Filter, size = 16.dp)
        }
    }
}

@Composable
fun PhotoGallery(
    photos: List<com.stephen.ricohgrlinkpro.model.PhotoItem>,
    isGrid: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isGrid) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(photos, key = { it.id }) { photo ->
                PhotoGridItem(photo = photo, isListMode = false)
            }
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(photos, key = { it.id }) { photo ->
                PhotoGridItem(photo = photo, isListMode = true)
            }
        }
    }
}
