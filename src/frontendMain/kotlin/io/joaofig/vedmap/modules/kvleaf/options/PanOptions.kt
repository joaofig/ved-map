package io.joaofig.vedmap.modules.kvleaf.options

open class PanOptions(
    animate: Boolean? = null,
    duration: Number = 0.25,
    easeLinearity: Number = 0.25,
    noMoveStart: Boolean = false
) : ZoomOptions(animate) {
    init {
        jsObject["duration"] = duration
        jsObject["easeLinearity"] = easeLinearity
        jsObject["noMoveStart"] = noMoveStart
    }
}