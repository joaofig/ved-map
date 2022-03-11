package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.models.GeoBounds
import io.kvision.annotations.KVService

@KVService
interface IClusterService {
    suspend fun getClusterBounds(): GeoBounds
    suspend fun getClusters(): List<Cluster>
}