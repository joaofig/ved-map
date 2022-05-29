/*
 * Copyright (c) 2017-present Robert Jaros
 * Copyright (c) 2020-present Jörg Rade
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.joaofig.vedmap.controls

import io.kvision.maps.LeafletObjectFactory
import io.kvision.MapsModule
import io.kvision.core.Container
import io.kvision.core.Widget
import io.kvision.maps.externals.leaflet.map.LeafletMap
import io.kvision.snabbdom.VNode
import io.kvision.utils.obj
import org.w3c.dom.HTMLElement

/**
 * Maps component.
 */
open class ContextMenuMap(
    className: String? = null,
    init: (ContextMenuMap.() -> Unit) = {}
) : Widget(className) {

    var options = obj<ContextMenuMapOptions> { }

    /** private backing field - use [configureLeafletMap] or [leafletMap] for accessing */
    private var _leafletMap: LeafletMap? = null

    /** @see [configureLeafletMap] */
    private var leafletMapConfigurer: LeafletMap.() -> Unit = { }

    init {
        useSnabbdomDistinctKey()
        this.init()
    }

    /**
     * Apply some configuration to [Leaflet Map][LeafletMap].
     *
     * It is **safe** to use this method before the [KVision Maps][Maps] Widget
     * is initialised, and is added to a parent element.
     *
     * If the widget is initialised, then this configuration will be applied immediately.
     * If not, then the configuration will be stored and applied during initialisation.
     *
     * (note: invoking this method will overwrite previously stored
     * configurations).
     */
    fun configureLeafletMap(configure: LeafletMap.() -> Unit) {
        leafletMapConfigurer = configure
        _leafletMap?.leafletMapConfigurer()
    }

    /**
     * Perform an action with the [LeafletMap] instance.
     *
     * It is **unsafe** to use this method before the [KVision Maps][Maps] Widget
     * is initialised, and is added to a parent element. Instead, use [configureLeafletMap].
     *
     * @throws IllegalArgumentException if the `LeafletMap` is not yet initialized - it must first
     * be added as a component.
     */
    fun <T : Any?> leafletMap(action: LeafletMap.() -> T): T {
        return requireNotNull(_leafletMap) {
            "LeafletMap is not initialised - the KVision Maps widget must be added to the DOM"
        }.action()
    }

    /** Create a native map instance. */
    override fun afterInsert(node: VNode) {
        val thisElement: HTMLElement = requireNotNull(this.getElement() as? HTMLElement) {
            "$this - Unable to get HTMLElement"
        }

        _leafletMap = LeafletMap(thisElement, options)
        _leafletMap!!.leafletMapConfigurer()
    }

    override fun afterDestroy() {
        if (_leafletMap != null) {
            _leafletMap!!.remove()
            _leafletMap = null
        }
    }

    companion object {
        init {
            MapsModule.initialize()
        }

        /** Equivalent to Leaflet's `L` shortcut. */
        val L: LeafletObjectFactory = LeafletObjectFactory
    }

}

/**
 * DSL builder extension function.
 *
 * It takes the same parameters as the constructor of the [Maps] component.
 */
fun Container.contextMenuMap(
    className: String? = null,
    init: (ContextMenuMap.() -> Unit) = {}
): ContextMenuMap {
    val maps = ContextMenuMap(className, init)
    this.add(maps)
    return maps
}
