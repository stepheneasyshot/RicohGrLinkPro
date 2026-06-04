package com.stephen.ricohgrlinkpro.model

enum class ConnectionStatus {
    Disconnected,
    Pairing,
    Connected,
}

data class CameraInfo(
    val model: String = "RICOH GR IIIx",
    val serial: String = "9820-XXXX",
    val batteryPercent: Int = 84,
)

data class RemoteSettings(
    val exposure: Float = -0.3f,
    val iso: Int = 400,
    val aperture: String = "F 2.8",
    val shutterSpeed: String = "SS 1/250",
    val ev: String = "EV -0.3",
    val selectedFilmSim: String = "Positive Film",
    val whiteBalance: String = "Auto",
    val focusMode: String = "Auto-Area",
)

data class FilmSimulation(
    val name: String,
    val subtitle: String,
)
