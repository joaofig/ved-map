package io.joaofig.vedmap.models

import kotlinx.serialization.Serializable

@Serializable
data class MapPoint(
    val id:             Int = 0,
    val dayNumber:      Double = 0.0,
    val vehicleId:      Int = 0,
    val tripId:         Int = 0,
    val timestamp:      Int = 0,
    val latitude:       Double = 0.0,
    val longitude:      Double = 0.0,
    val speed:          Double? = null,
    val maf:            Double? = null,
    val rpm:            Double? = null,
    val absoluteLoad:   Double? = null
)