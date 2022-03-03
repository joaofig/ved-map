package io.joaofig.vedmap.clients

import io.joaofig.vedmap.models.GeoBounds
import io.joaofig.vedmap.services.ClusterService

object ClusterClient {
    private val service = ClusterService()

    suspend fun getClusterBounds(): GeoBounds {
        return service.getClusterBounds()
    }
}