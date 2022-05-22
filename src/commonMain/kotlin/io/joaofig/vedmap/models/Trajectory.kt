package io.joaofig.vedmap.models

@kotlinx.serialization.Serializable
data class Trajectory(
    val tripId: Int,
    val points: List<MapPoint>
)
