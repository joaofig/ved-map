package io.joaofig.vedmap.modules.kvleaf

import io.joaofig.vedmap.modules.LeafletModule.leaflet as L

class Bounds(private val jsObject: dynamic) {
    constructor(corner1: Point, corner2: Point): this(L.bounds(corner1.toDynamic(), corner2.toDynamic())) { }

    fun extend(pt: Point): Bounds {
        jsObject.extend(pt.toDynamic())
        return this
    }

    fun getCenter(round: Boolean = false) = Point(jsObject.getCenter(round))
    fun getSize() = Point(jsObject.getSize())
}