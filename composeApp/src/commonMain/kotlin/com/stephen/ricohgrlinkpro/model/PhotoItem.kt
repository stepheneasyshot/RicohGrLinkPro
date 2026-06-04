package com.stephen.ricohgrlinkpro.model

import androidx.compose.ui.graphics.Color

data class PhotoItem(
    val id: String,
    val fileName: String,
    val aperture: String,
    val shutterSpeed: String,
    val iso: String,
    val placeholderColor: Color,
    val aspectRatio: Float = 1f,
)

data class FolderItem(
    val id: String,
    val name: String,
    val path: String,
    val photoCount: Int,
)
