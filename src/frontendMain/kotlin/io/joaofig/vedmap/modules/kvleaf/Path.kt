package io.joaofig.vedmap.modules.kvleaf

open class Path(jsPath: dynamic): Layer(jsPath) {

    fun redraw(): Path {
        jsObject.redraw()
        return this
    }

    fun bringToFront(): Path {
        jsObject.bringToFront()
        return this
    }

    fun bringToBack(): Path {
        jsObject.bringToBack()
        return this
    }
}