package io.joaofig.vedmap.modules.kvleaf

open class Layer(jsObj: dynamic): Evented(jsObj) {

    fun remove(): Layer {
        jsObject.remove()
        return this
    }

    fun getAttribution(): String {
        return jsObject.getAttribution().toString()
    }
}