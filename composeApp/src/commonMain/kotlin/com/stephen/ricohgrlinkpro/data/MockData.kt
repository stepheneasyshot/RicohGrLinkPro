package com.stephen.ricohgrlinkpro.data

import androidx.compose.ui.graphics.Color
import com.stephen.ricohgrlinkpro.model.FilmSimulation
import com.stephen.ricohgrlinkpro.model.FolderItem
import com.stephen.ricohgrlinkpro.model.PhotoItem

object MockData {
    val collectionTitle = "Street Chronicles"
    val collectionSubtitle = "Tokyo Shinjuku Session • Oct 2023"

    val photos = listOf(
        PhotoItem("1", "DSC_0001.RW2", "F2.8", "1/250", "ISO 400", Color(0xFF2C2C2C), 0.75f),
        PhotoItem("2", "DSC_0002.RW2", "F2.8", "1/125", "ISO 800", Color(0xFF1A1A1A), 1.2f),
        PhotoItem("3", "DSC_0003.RW2", "F4.0", "1/500", "ISO 200", Color(0xFF3D3D3D), 0.85f),
        PhotoItem("4", "DSC_0004.RW2", "F2.8", "1/60", "ISO 1600", Color(0xFF252525), 1f),
        PhotoItem("5", "DSC_0005.RW2", "F5.6", "1/200", "ISO 400", Color(0xFF333333), 0.9f),
        PhotoItem("6", "DSC_0006.RW2", "F2.8", "1/1000", "ISO 100", Color(0xFF1E1E1E), 1.1f),
        PhotoItem("7", "DSC_0007.RW2", "F4.0", "1/320", "ISO 640", Color(0xFF2A2A2A), 0.8f),
        PhotoItem("8", "DSC_0008.RW2", "F2.8", "1/80", "ISO 1250", Color(0xFF383838), 1f),
        PhotoItem("9", "DSC_0009.RW2", "F8.0", "1/30", "ISO 3200", Color(0xFF222222), 1.3f),
        PhotoItem("10", "DSC_0010.RW2", "F2.8", "1/400", "ISO 320", Color(0xFF303030), 0.95f),
        PhotoItem("11", "DSC_0011.RW2", "F4.5", "1/160", "ISO 500", Color(0xFF282828), 1.05f),
        PhotoItem("12", "DSC_0012.RW2", "F2.8", "1/640", "ISO 200", Color(0xFF353535), 0.88f),
    )

    val folders = listOf(
        FolderItem("f1", "GR Photos", "~/Desktop/GR Photos", 248),
        FolderItem("f2", "Street Work", "~/Pictures/Street Work", 156),
        FolderItem("f3", "Downloads", "~/Downloads", 42),
        FolderItem("f4", "Archive 2023", "~/Pictures/Archive/2023", 891),
    )

    val filmSimulations = listOf(
        FilmSimulation("Positive Film", "VIBRANT & CLASSIC"),
        FilmSimulation("Monochrome", "HIGH CONTRAST B&W"),
        FilmSimulation("Negative Std", "SOFT & WARM"),
        FilmSimulation("High Contrast", "BOLD & PUNCHY"),
    )
}
