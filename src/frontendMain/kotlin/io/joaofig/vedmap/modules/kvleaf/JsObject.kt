package io.joaofig.vedmap.modules.kvleaf

open class JsObject(protected val jsObject: dynamic) {

    fun toDynamic() = jsObject
}