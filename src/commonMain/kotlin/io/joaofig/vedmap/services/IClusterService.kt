package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.models.GeoBounds
import io.joaofig.vedmap.models.GeoMultiPolygon
import io.kvision.annotations.KVService

@KVService
interface IClusterService {
    suspend fun getClusterBounds(): GeoBounds
    suspend fun getClusters(): List<Cluster>
    suspend fun getClusterPolygon(clusterId: Int): GeoMultiPolygon
}