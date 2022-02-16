package io.joaofig.vedmap.modules.kvleaf

import io.joaofig.vedmap.modules.LeafletModule
import io.joaofig.vedmap.modules.kvleaf.options.MapOptions
import io.kvision.core.Widget
import io.kvision.snabbdom.VNode
import org.w3c.dom.HTMLElement

open class MapControl(
    private val mapOptions: MapOptions? = null,
    className: String? = null) : Widget(className) {
    private var map: dynamic = null

    init {
        useSnabbdomDistinctKey()
    }

    override fun afterInsert(node: VNode) {
        (this.getElement() as? HTMLElement)?.let {
            createMap(it, mapOptions)
        }
    }

    private fun createMap(element: HTMLElement, mapOptions: MapOptions?) {
        map = if (mapOptions == null) {
            LeafletModule.leaflet.map(element)
        } else {
            LeafletModule.leaflet.map(element, mapOptions.toDynamic())
        }
    }

    fun setView(center: LatLng, zoom: Number): MapControl {
        map.setView(center.toDynamic(), zoom)
        return this
    }

    val conversions by lazy { MapConversions(map) }
}