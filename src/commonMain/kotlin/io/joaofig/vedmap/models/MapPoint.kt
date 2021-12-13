package io.joaofig.vedmap.models

import kotlinx.serialization.Serializable

@Serializable
data class MapPoint(
    val id:             Int = 0,
    val dayNumber:      Double = 0.0,
    val vehicleId:      Int = 0,
    val tripId:         Int = 0,
    val timeStamp:      Int = 0,
    val latitude:       Double = 0.0,
    val longitude:      Double = 0.0,
    val speed:          Double = 0.0,
    val maf:            Double = 0.0,
    val rpm:            Double = 0.0,
    val absoluteLoad:   Double = 0.0
)