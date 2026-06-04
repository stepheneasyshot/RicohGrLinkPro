package com.stephen.ricohgrlinkpro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.stephen.ricohgrlinkpro.data.MockData
import com.stephen.ricohgrlinkpro.model.CameraInfo
import com.stephen.ricohgrlinkpro.model.ConnectionStatus
import com.stephen.ricohgrlinkpro.model.LibraryViewMode
import com.stephen.ricohgrlinkpro.model.RemoteSettings
import com.stephen.ricohgrlinkpro.model.SettingsState
import com.stephen.ricohgrlinkpro.model.SidebarNavItem
import com.stephen.ricohgrlinkpro.model.TopTab

class AppViewModel {
    var selectedNavItem by mutableStateOf(SidebarNavItem.CameraConnection)
        private set
    var selectedTopTab by mutableStateOf(TopTab.Remote)
        private set
    var connectionStatus by mutableStateOf(ConnectionStatus.Pairing)
        private set
    var isCameraConnected by mutableStateOf(false)
    var libraryViewMode by mutableStateOf(LibraryViewMode.Grid)
    var settings by mutableStateOf(SettingsState())
        private set
    var remoteSettings by mutableStateOf(RemoteSettings())
        private set
    var selectedFolderId by mutableStateOf(MockData.folders.first().id)
        private set
    var pairingProgress by mutableFloatStateOf(0.65f)
        private set

    val cameraInfo = CameraInfo()
    val photos = MockData.photos
    val folders = MockData.folders
    val filmSimulations = MockData.filmSimulations

    fun selectNavItem(item: SidebarNavItem) {
        selectedNavItem = item
        when (item) {
            SidebarNavItem.CameraConnection -> selectedTopTab = TopTab.Remote
            SidebarNavItem.LocalPhotos, SidebarNavItem.Folders -> selectedTopTab = TopTab.Library
            SidebarNavItem.Settings -> Unit
        }
    }

    fun selectTopTab(tab: TopTab) {
        selectedTopTab = tab
    }

    fun toggleCameraConnection() {
        isCameraConnected = !isCameraConnected
        connectionStatus = if (isCameraConnected) {
            ConnectionStatus.Connected
        } else {
            ConnectionStatus.Pairing
        }
    }

    fun initializeLink() {
        isCameraConnected = true
        connectionStatus = ConnectionStatus.Connected
    }

    fun setLibraryViewMode(isGrid: Boolean) {
        libraryViewMode = if (isGrid) LibraryViewMode.Grid else LibraryViewMode.List
    }

    fun selectFolder(folderId: String) {
        selectedFolderId = folderId
    }

    fun updateSettings(update: SettingsState.() -> SettingsState) {
        settings = settings.update()
    }

    fun updateRemoteSettings(update: RemoteSettings.() -> RemoteSettings) {
        remoteSettings = remoteSettings.update()
    }
}
