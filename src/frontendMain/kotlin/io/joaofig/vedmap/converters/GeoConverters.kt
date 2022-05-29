package io.joaofig.vedmap.converters

import io.joaofig.vedmap.models.GeoBounds
import io.joaofig.vedmap.models.GeoMultiPolygon
import io.joaofig.vedmap.models.Trajectory
import io.kvision.maps.externals.geojson.LineString
import io.kvision.maps.externals.geojson.MultiPolygon
import io.kvision.maps.externals.leaflet.geo.LatLng
import io.kvision.maps.externals.leaflet.geo.LatLngBounds
import io.kvision.maps.externals.leaflet.layer.vector.Polygon
import io.kvision.maps.externals.leaflet.layer.vector.Polyline
import io.kvision.utils.obj

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

fun GeoMultiPolygon.toMultiPolygon(options: Polyline.PolylineOptions): Polygon<MultiPolygon> {
    return Polygon(this.toArray(), options)
}

fun <T: Polyline.PolylineOptions> GeoMultiPolygon.toMultiPolygon(configure: T.() -> Unit = {}): Polygon<MultiPolygon>{
    return Polygon(
        latlngs = this.toArray(),
        options = obj<T>(configure)
    )
}

fun GeoBounds.toLatLngBounds(): LatLngBounds {
    return LatLngBounds(
        LatLng(minLat, minLon),
        LatLng(maxLat, maxLon)
    )
}

fun Trajectory.toPolyline(): Polyline<LineString> {
    return Polyline(
        points
            .map { LatLng(it.latitude, it.longitude) }
            .toTypedArray()
    )
}

fun Trajectory.toPolyline(options: Polyline.PolylineOptions): Polyline<LineString> {
    return Polyline(
        points
            .map { LatLng(it.latitude, it.longitude) }
            .toTypedArray(),
        options
    )
}
