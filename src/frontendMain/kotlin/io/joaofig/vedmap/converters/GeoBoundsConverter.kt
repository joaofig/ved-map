package io.joaofig.vedmap.converters

import io.joaofig.vedmap.models.GeoBounds
import io.kvision.maps.externals.leaflet.geo.LatLng
import io.kvision.maps.externals.leaflet.geo.LatLngBounds

object GeoBoundsConverter {
    fun toLatLngBounds(geoBounds: GeoBounds): LatLngBounds {
        return LatLngBounds(
            LatLng(geoBounds.minLat, geoBounds.minLon),
            LatLng(geoBounds.maxLat, geoBounds.maxLon)
        )
    }
}