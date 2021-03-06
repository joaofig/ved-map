package io.joaofig.vedmap.views

import io.joaofig.vedmap.converters.toMultiPolygon
import io.joaofig.vedmap.models.GeoMultiPolygon
import io.kvision.maps.externals.geojson.MultiPolygon
import io.kvision.maps.externals.leaflet.layer.vector.Polygon

class MapClusterView(
    val id: Int,
    val name: String,
    private val polygon: GeoMultiPolygon
) {
    fun getMapShape(): Polygon<MultiPolygon> {
        return polygon.toMultiPolygon()
    }
}