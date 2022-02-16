package io.joaofig.vedmap.modules.kvleaf.options

import io.joaofig.vedmap.modules.kvleaf.JsObject

open class ZoomOptions(animate: Boolean? = null) : JsObject({}.asDynamic()) {
    init {
        jsObject["animate"] = animate
    }
}