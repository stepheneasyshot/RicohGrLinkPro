package com.stephen.ricohgrlinkpro.model

enum class SidebarNavItem(val label: String) {
    CameraConnection("Camera Connection"),
    LocalPhotos("Local Photos"),
    Folders("Folders"),
    Settings("Settings"),
}

enum class TopTab(val label: String) {
    Remote("Remote"),
    Library("Library"),
    Editor("Editor"),
}

enum class LibraryViewMode {
    Grid,
    List,
}

enum class AppearanceMode(val label: String) {
    Light("Light"),
    Dark("Dark"),
    Auto("Auto"),
}
