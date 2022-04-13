package io.joaofig.vedmap.controls

import io.kvision.ModuleInitializer
import io.kvision.maps.externals.leaflet.geo.LatLng
import io.kvision.maps.externals.leaflet.geometry.Point
import io.kvision.maps.externals.leaflet.layer.vector.Polyline
import io.kvision.maps.externals.leaflet.layer.vector.Renderer

interface CtxMenuItemEvent {
    var latlng: LatLng?
    var containerPoint: Point?
    var layerPoint: Point?
    var relatedTarget: dynamic
}

typealias LeafletContextMenuEventHandlerFn = (event: CtxMenuItemEvent) -> Unit

class CtxMenuItem(
    val text: String? = null,
    val icon: String? = null,
    val retinaIcon: String? = null,
    val iconCls: String? = null,
    val retinaIconCls: String? = null,
    val context: dynamic = null,
    val disabled: Boolean? = false,
    val separator: Boolean? = null,
    val hideOnSelect: Boolean? = true,
    val callback: LeafletContextMenuEventHandlerFn? = null
)

class CtxPolylineOptions : Polyline.PolylineOptions {
    var contextmenu: Boolean? = null
    var contextmenuWidth: Number? = null
    var contextmenuAnchor: Point? = null
    var contextmenuItems: Array<CtxMenuItem>? = null
    override var attribution: String? = null
    override var bubblingMouseEvents: Boolean? = null
    override var className: String? = null
    override var color: String? = null
    override var dashArray: dynamic = null
    override var dashOffset: String? = null
    override var fill: Boolean? = null
    override var fillColor: String? = null
    override var fillOpacity: Number? = null
    override var fillRule: String? = null
    override var interactive: Boolean? = null
    override var lineCap: String? = null
    override var lineJoin: String? = null
    override var noClip: Boolean? = null
    override var opacity: Number? = null
    override var pane: String? = null
    override var renderer: Renderer? = null
    override var smoothFactor: Number? = null
    override var stroke: Boolean? = null
    override var weight: Number? = null
}

object ContextMenuModule : ModuleInitializer {
    val ctxLeaflet = io.kvision.require("leaflet-contextmenu")

    override fun initialize() {
    }
}

class MapContextMenu() {
    val leafletContextMenu = io.kvision.require("leaflet-contextmenu")

    init {
        io.kvision.require("leaflet-contextmenu/dist/leaflet.contextmenu.css")
    }
}