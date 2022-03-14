package io.joaofig.vedmap.models

@kotlinx.serialization.Serializable
data class GeoLocation(
    val latitude: Double,
    val longitude: Double
)

@kotlinx.serialization.Serializable
data class GeoPolyline(val points: List<GeoLocation>)

@kotlinx.serialization.Serializable
data class GeoPolygon(val rings: List<GeoPolyline>)

@kotlinx.serialization.Serializable
data class GeoMultiPolygon(val polygons: List<GeoPolygon>)

@kotlinx.serialization.Serializable
data class GeoBounds(
    val minLat: Double,
    val maxLat: Double,
    val minLon: Double,
    val maxLon: Double
)
