package io.joaofig.vedmap.models

import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
    val id:             Int = 0,
    val vehicleType:    String? = "",
    val vehicleClass:   String? = "",
    val engine:         String? = "",
    val transmission:   String? = "",
    val driveWheels:    String? = "",
    val weight:         Double? = 0.0
)