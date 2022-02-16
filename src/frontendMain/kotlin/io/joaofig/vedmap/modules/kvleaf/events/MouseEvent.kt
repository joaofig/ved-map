package io.joaofig.vedmap.modules.kvleaf.events

import io.joaofig.vedmap.modules.kvleaf.LatLng
import io.joaofig.vedmap.modules.kvleaf.Point

class MouseEvent : MapEvent() {

    var latLng: LatLng
        get() { return LatLng(jsObject["latLng"]) }
        set(value) { jsObject["latLng"] = value.toDynamic() }

    var layerPoint: Point
        get() { return Point(jsObject["layerPoint"]) }
        set(value) { jsObject["layerPoint"] = value.toDynamic() }

    var containerPoint: Point
        get() { return Point(jsObject["containerPoint"]) }
        set(value) { jsObject["containerPoint"] = value.toDynamic() }

    var originalEvent: dynamic
        get() { return jsObject["originalEvent"] }
        set(value) { jsObject["originalEvent"] = value }
}