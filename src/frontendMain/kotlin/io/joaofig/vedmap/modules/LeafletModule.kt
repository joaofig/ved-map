/*
 * Copyright (c) 2017-present Robert Jaros
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
package io.joaofig.vedmap.modules

import io.kvision.ModuleInitializer
import io.kvision.utils.delete
import io.kvision.utils.obj
import io.kvision.require

/**
 * Initializer for KVision maps module.
 */
object LeafletModule : ModuleInitializer {

    val leaflet = require("leaflet")

    init {
        leaflet.Icon.Default.imagePath = ""
        delete(leaflet.Icon.Default.prototype._getIconUrl)
        leaflet.Icon.Default.mergeOptions(obj {
            iconRetinaUrl = require("leaflet/dist/images/marker-icon-2x.png")
            iconUrl = require("leaflet/dist/images/marker-icon.png")
            shadowUrl = require("leaflet/dist/images/marker-shadow.png")
        })
    }

    override fun initialize() {
        require("leaflet/dist/leaflet.css")
    }
}
