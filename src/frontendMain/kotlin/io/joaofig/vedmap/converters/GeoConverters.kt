package io.joaofig.vedmap.converters

import io.joaofig.vedmap.models.GeoBounds
import io.joaofig.vedmap.models.GeoMultiPolygon
import io.kvision.maps.externals.geojson.MultiPolygon
import io.kvision.maps.externals.leaflet.geo.LatLng
import io.kvision.maps.externals.leaflet.geo.LatLngBounds
import io.kvision.maps.externals.leaflet.layer.vector.Polygon

fun GeoMultiPolygon.toArray(): Array<Array<Array<LatLng>>> {
    return this.polygons.map { p ->
        p.rings.map { r ->
            r.points.map {
                LatLng(it.latitude, it.longitude)
            }.toTypedArray()
        }.toTypedArray()
    }.toTypedArray()
}

fun GeoMultiPolygon.toMultiPolygon(): Polygon<MultiPolygon> {
    return Polygon(this.toArray())
}

fun GeoBounds.toLatLngBounds(): LatLngBounds {
    return LatLngBounds(
        LatLng(minLat, minLon),
        LatLng(maxLat, maxLon)
    )
}
