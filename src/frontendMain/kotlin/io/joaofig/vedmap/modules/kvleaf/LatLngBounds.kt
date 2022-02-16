package io.joaofig.vedmap.modules.kvleaf

import io.joaofig.vedmap.modules.LeafletModule.leaflet as L

class LatLngBounds(latLngBounds: dynamic) : JsObject(latLngBounds) {

    constructor(corner1: LatLng, corner2: LatLng): this(L.latLngBounds(corner1.toDynamic(), corner2.toDynamic())) { }

    fun extend(latLng: LatLng) = LatLngBounds(jsObject.extend(latLng.toDynamic()))
    fun extend(latLngBounds: LatLngBounds) = LatLngBounds(jsObject.extend(latLngBounds.toDynamic()))
    fun pad(bufferRatio: Double) = LatLngBounds(jsObject.pad(bufferRatio))

    val center: LatLng
        get() = LatLng(jsObject.getCenter())
    val north: Double
        get() = jsObject.getNorth() as Double
    val south: Double
        get() = jsObject.getSouth() as Double
    val east: Double
        get() = jsObject.getEast() as Double
    val west: Double
        get() = jsObject.getWest() as Double
    val southWest: LatLng
        get() = LatLng(jsObject.getSouthWest())
    val southEast: LatLng
        get() = LatLng(jsObject.getSouthEast())
    val northEast: LatLng
        get() = LatLng(jsObject.getNorthEast())
    val northWest: LatLng
        get() = LatLng(jsObject.getNorthWest())
    val isValid: Boolean
        get() = jsObject.isValid() as Boolean

    fun contains(latLngBounds: LatLngBounds) = jsObject.contains(latLngBounds.toDynamic()) as Boolean
    fun contains(latLng: LatLng) = jsObject.contains(latLng.toDynamic()) as Boolean
    fun intersects(latLngBounds: LatLngBounds) = jsObject.intersects(latLngBounds.toDynamic()) as Boolean
    fun overlaps(latLngBounds: LatLngBounds) = jsObject.overlaps(latLngBounds.toDynamic()) as Boolean

    fun toBBoxString() = jsObject.toBBoxString() as String

    fun equals(latLngBounds: LatLngBounds) = jsObject.equals(latLngBounds.toDynamic()) as Boolean
    fun equals(latLngBounds: LatLngBounds, maxMargin: Double) =
        jsObject.equals(latLngBounds.toDynamic(), maxMargin) as Boolean

    companion object {
        fun fromArray(locations: List<LatLng>): LatLngBounds {
            return LatLngBounds(L.latLngBounds(locations.map { it.toDynamic() }))
        }
    }
}