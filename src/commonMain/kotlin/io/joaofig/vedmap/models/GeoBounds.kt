package io.joaofig.vedmap.models

import kotlinx.serialization.Serializable

@Serializable
data class GeoBounds(
    val minLat: Double,
    val maxLat: Double,
    val minLon: Double,
    val maxLon: Double)
