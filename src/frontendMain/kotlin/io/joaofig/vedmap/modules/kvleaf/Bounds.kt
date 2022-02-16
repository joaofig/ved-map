package io.joaofig.vedmap.modules.kvleaf

import io.joaofig.vedmap.modules.LeafletModule.leaflet as L

class Bounds(bounds: dynamic) : JsObject(bounds) {
    constructor(corner1: Point, corner2: Point): this(L.bounds(corner1.toDynamic(), corner2.toDynamic())) { }

    fun extend(pt: Point): Bounds {
        jsObject.extend(pt.toDynamic())
        return this
    }

    fun getCenter(round: Boolean = false) = Point(jsObject.getCenter(round))

    val size: Point
        get() = Point(jsObject.getSize())
    val bottomLeft: Point
        get() = Point(jsObject.getBottomLeft())
    val topRight: Point
        get() = Point(jsObject.getTopRight())
    val topLeft: Point
        get() = Point(jsObject.getTopLeft())
    val bottomRight: Point
        get() = Point(jsObject.getBottomRight())

    val min: Point
        get() = Point(jsObject.min())
    val max: Point
        get() = Point(jsObject.max())

    fun contains(pt: Point): Boolean = jsObject.contains(pt.toDynamic()) as Boolean
    fun contains(bounds: Bounds): Boolean = jsObject.contains(bounds.toDynamic()) as Boolean
    fun intersects(bounds: Bounds): Boolean = jsObject.intersects(bounds.toDynamic()) as Boolean
    fun overlaps(bounds: Bounds): Boolean = jsObject.overlaps(bounds.toDynamic()) as Boolean
}