package io.joaofig.vedmap.modules.kvleaf.options

import io.joaofig.vedmap.modules.kvleaf.Point

open class FitBoundsOptions(
    paddingTopLeft: Point = Point(0.0, 0.0),
    paddingBottomRight: Point = Point(0.0, 0.0),
    padding: Point = Point(0.0, 0.0),
    maxZoom: Number? = null,
    animate: Boolean? = null,
    duration: Number = 0.25,
    easeLinearity: Number = 0.25,
    noMoveStart: Boolean = false
) : PanOptions(animate, duration, easeLinearity, noMoveStart) {
    init {
        jsObject["paddingTopLeft"] = paddingTopLeft.toDynamic()
        jsObject["paddingBottomRight"] = paddingBottomRight.toDynamic()
        jsObject["padding"] = padding.toDynamic()
        jsObject["maxZoom"] = maxZoom
    }
}