package io.joaofig.vedmap.modules.kvleaf

import io.joaofig.vedmap.modules.LeafletModule.leaflet as L

class Point(private val jsObject: dynamic) {

    constructor(x: Double, y: Double, round: Boolean = false) : this(L.point(x, y, round)) { }

    fun toDynamic() = jsObject

    val x: Double
        get() = jsObject.x as Double
    val y: Double
        get() = jsObject.y as Double

    fun clone() = Point(jsObject.clone())

    fun add(pt: Point) = Point(jsObject.add(pt.toDynamic()))

    fun subtract(pt: Point) = Point(jsObject.subtract(pt.toDynamic()))
    fun multiplyBy(num: Double) = Point(jsObject.multiplyBy(num))
    fun divideBy(num: Double) = Point(jsObject.divideBy(num))
    fun scaleBy(pt: Point) = Point(jsObject.scaleBy(pt.toDynamic()))
    fun unscaleBy(pt: Point) = Point(jsObject.unscaleBy(pt.toDynamic()))

    fun distanceTo(pt: Point): Double = jsObject.distanceTo(pt.toDynamic()) as Double

    fun round() = Point(jsObject.round())
    fun floor() = Point(jsObject.floor())
    fun ceil()  = Point(jsObject.ceil())
    fun trunc() = Point(jsObject.trunc())
}