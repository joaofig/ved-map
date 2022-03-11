package io.joaofig.vedmap.models

data class ClusterPoint(
    val id: Int = 0,
    val tripLimitId: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val h3: String = ""
)
