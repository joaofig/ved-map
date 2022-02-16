package io.joaofig.vedmap.modules.kvleaf

open class Evented(evented: dynamic) : JsObject(evented) {

    fun listens(type: String): Boolean {
        return jsObject.listens(type) == true
    }

    fun off(): Evented {
        jsObject.off()
        return this
    }
}