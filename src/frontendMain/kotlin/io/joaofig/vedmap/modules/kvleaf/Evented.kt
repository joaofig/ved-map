package io.joaofig.vedmap.modules.kvleaf

open class Evented(protected val jsObject: dynamic) {

    fun toDynamic() = jsObject

    fun listens(type: String): Boolean {
        return jsObject.listens(type) == true
    }
}