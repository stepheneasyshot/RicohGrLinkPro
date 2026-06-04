package com.stephen.ricohgrlinkpro.model

data class SettingsState(
    val launchAtLogin: Boolean = true,
    val appearance: AppearanceMode = AppearanceMode.Dark,
    val defaultSavePath: String = "~/Pictures/GR Link/Imports",
    val autoImportOnConnection: Boolean = false,
    val keepRawFiles: Boolean = true,
    val applyLensProfile: Boolean = true,
    val autoSyncLibrary: Boolean = true,
    val cloudPhotosUsed: Int = 4208,
    val cloudStorageUsedGb: Int = 842,
    val cloudStorageTotalGb: Int = 2048,
)
