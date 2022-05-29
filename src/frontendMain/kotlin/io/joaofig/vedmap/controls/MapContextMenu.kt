package io.joaofig.vedmap.controls

import io.kvision.ModuleInitializer
import io.kvision.maps.externals.leaflet.geo.LatLng
import io.kvision.maps.externals.leaflet.geometry.Point
import io.kvision.maps.externals.leaflet.layer.vector.Polyline
import io.kvision.maps.externals.leaflet.map.LeafletMap

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

interface ContextMenuPolylineOptions : Polyline.PolylineOptions {
    var contextmenu: Boolean?
    var contextmenuWidth: Number?
    var contextmenuAnchor: Point?
    var contextmenuItems: Array<CtxMenuItem>?
    var contextmenuInheritItems: Boolean?
}

interface ContextMenuMapOptions : LeafletMap.LeafletMapOptions {
    var contextmenu: Boolean?
    var contextmenuWidth: Number?
    var contextmenuAnchor: Point?
    var contextmenuItems: Array<CtxMenuItem>?
    var contextmenuInheritItems: Boolean?
}

//
//open class PolylineOptions(
//    override var contextmenu: Boolean? = null,
//    override var contextmenuWidth: Number? = null,
//    override var contextmenuAnchor: Point? = null,
//    override var contextmenuItems: Array<CtxMenuItem>? = null,
//    override var contextmenuInheritItems: Boolean? = false,
//    override var bubblingMouseEvents: Boolean? = null,
//    override var interactive: Boolean? = null,
//    override var attribution: String? = null,
//    override var pane: String? = null,
//    override var className: String? = null,
//    override var color: String? = null,
//    override var dashArray: dynamic = null,
//    override var dashOffset: String? = null,
//    override var fill: Boolean? = null,
//    override var fillColor: String? = null,
//    override var fillOpacity: Number? = null,
//    override var fillRule: String? = null,
//    override var lineCap: String? = null,
//    override var lineJoin: String? = null,
//    override var opacity: Number? = null,
//    override var renderer: Renderer? = null,
//    override var stroke: Boolean? = null,
//    override var weight: Number? = null,
//    override var noClip: Boolean? = null,
//    override var smoothFactor: Number? = null
//) : ContextMenuPolylineOptions


//class ContextMenuMap(
//    className: String? = null,
//    init: (Maps.() -> Unit) = {}
//) : Maps(className, init) {
//    private var leafletMapConfigurer: LeafletMap.() -> Unit = { }
//}

object MapContextMenuModule : ModuleInitializer {
    val leafletContextMenu = io.kvision.require("leaflet-contextmenu")

    override fun initialize() {
        io.kvision.require("leaflet-contextmenu/dist/leaflet.contextmenu.min.css")
    }
}
