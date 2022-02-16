package io.joaofig.vedmap.modules.kvleaf.options

import io.joaofig.vedmap.modules.kvleaf.JsObject
import io.joaofig.vedmap.modules.kvleaf.LatLng

class MapOptions(
    preferCanvas: Boolean = false,
    attributionControl: Boolean = true,
    zoomControl: Boolean = true,
    closePopupOnClick: Boolean = true,
    zoomSnap: Number = 1,
    zoomDelta: Number = 1,
    trackResize: Boolean = true,
    boxZoom: Boolean = true,
    doubleClickZoom: Boolean = true,
    dragging: Boolean = true,
    // TODO: Declare the crs parameter
    center: LatLng? = null,
    zoom: Number? = null,

    zoomAnimation: Boolean = true,
    zoomAnimationThreshold: Number = 4,
    fadeAnimation: Boolean = true,
    markerZoomAnimation: Boolean = true
) : JsObject({}.asDynamic()) {

    init {
        jsObject["preferCanvas"]            = preferCanvas
        jsObject["attributionControl"]      = attributionControl
        jsObject["zoomControl"]             = zoomControl
        jsObject["closePopupOnClick"]       = closePopupOnClick
        jsObject["zoomSnap"]                = zoomSnap
        jsObject["zoomDelta"]               = zoomDelta
        jsObject["trackResize"]             = trackResize
        jsObject["boxZoom"]                 = boxZoom
        jsObject["doubleClickZoom"]         = doubleClickZoom
        jsObject["dragging"]                = dragging
        jsObject["center"]                  = center
        jsObject["zoom"]                    = zoom

        jsObject["zoomAnimation"]           = zoomAnimation
        jsObject["zoomAnimationThreshold"]  = zoomAnimationThreshold
        jsObject["fadeAnimation"]           = fadeAnimation
        jsObject["markerZoomAnimation"]     = markerZoomAnimation
    }
}