package io.joaofig.vedmap.modules.kvleaf

import io.joaofig.vedmap.modules.LeafletModule.leaflet as L

class LatLng(latLng: dynamic) : JsObject(latLng) {

    constructor(lat: Double, lng: Double, alt: Double = 0.0): this(L.latlng(lat, lng, alt)) { }

    fun distance(latLng: LatLng): Double = jsObject.distanceTo(latLng.toDynamic()) as Double
    fun equals(latLng: LatLng): Boolean = jsObject.equals(latLng.toDynamic()) as Boolean
    fun equals(latLng: LatLng, maxMargin: Double): Boolean = jsObject.equals(latLng.toDynamic(), maxMargin) as Boolean

    fun wrap() = LatLng(jsObject.wrap())
    fun toBounds(sizeInMeters: Double) = LatLngBounds(jsObject.toBounds(sizeInMeters))

    val lat: Double
        get() = jsObject.lat as Double
    val lng: Double
        get() = jsObject.lng as Double
    val alt: Double
        get() = jsObject.alt as Double
}