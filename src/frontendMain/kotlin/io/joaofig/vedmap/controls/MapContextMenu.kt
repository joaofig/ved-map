package io.joaofig.vedmap.controls

import io.kvision.ModuleInitializer
import io.kvision.maps.externals.leaflet.geometry.Point
import io.kvision.maps.externals.leaflet.layer.vector.Polyline

//interface CtxMenuItemEvent {
//    var latlng: LatLng?
//    var containerPoint: Point?
//    var layerPoint: Point?
//    var relatedTarget: dynamic
//}
//
//typealias LeafletContextMenuEventHandlerFn = (event: CtxMenuItemEvent) -> Unit
//
//interface CtxMenuItem {
//    var text: String?
//    var icon: String?
//    var retinaIcon: String?
//    var iconCls: String?
//    var retinaIconCls: String?
//    var context: dynamic
//    var disabled: Boolean?
//    var separator: Boolean?
//    var hideOnSelect: Boolean?
//    var callback: LeafletContextMenuEventHandlerFn?
//}

interface ContextMenuPolylineOptions : Polyline.PolylineOptions {
    var contextmenu: Boolean?
    var contextmenuWidth: Number?
    var contextmenuAnchor: Point?
    var contextmenuItems: dynamic
    var contextmenuInheritItems: Boolean?
}

//interface ContextMenuMapOptions : LeafletMap.LeafletMapOptions {
//    var contextmenu: Boolean?
//    var contextmenuWidth: Number?
//    var contextmenuAnchor: Point?
//    var contextmenuItems: Array<CtxMenuItem>?
//    var contextmenuInheritItems: Boolean?
//}

object MapContextMenuModule : ModuleInitializer {
    val leafletContextMenu = io.kvision.require("leaflet-contextmenu")

    override fun initialize() {
        io.kvision.require("leaflet-contextmenu/dist/leaflet.contextmenu.min.css")
    }
}
