package io.joaofig.vedmap.modules.kvleaf.events

import io.joaofig.vedmap.modules.kvleaf.JsObject

open class MapEvent : JsObject({}.asDynamic()) {
    var type: String
        get() { return jsObject["type"].toString() }
        set(value) { jsObject["type"] = value }

    var target: dynamic
        get() { return jsObject["target"] }
        set(value) { jsObject["target"] = value }

    var sourceTarget: dynamic
        get() { return jsObject["sourceTarget"] }
        set(value) { jsObject["sourceTarget"] = value }

    var propagatedFrom: dynamic
        get() { return jsObject["propagatedFrom"] }
        set(value) { jsObject["propagatedFrom"] = value }
}
