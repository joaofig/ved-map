package io.joaofig.vedmap.models

import kotlinx.serialization.Serializable

@Serializable
data class Trip(
    val id:         Int = 0,
    val vehicleId:  Int = 0,
    val dayNumber:  Double = 0.0,
    val tsIni:      Int = 0,
    val tsEnd:      Int = 0,
    val clusterIni: Int = -1,
    val clusterEnd: Int = -1
)
