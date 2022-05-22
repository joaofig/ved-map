package io.joaofig.vedmap.repositories

import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.models.GeoMultiPolygon

object ClusterRepository {
    private val clusters = mutableListOf<Cluster>()
    private val polygons = mutableMapOf<Int,GeoMultiPolygon>()
    private val inbound = mutableMapOf<Int,List<Int>>()
    private val outbound = mutableMapOf<Int,List<Int>>()

    suspend fun getClusters(): List<Cluster> {
        if (clusters.isEmpty()) {
            clusters.addAll(ClusterClient.getClusters())
        }
        return clusters
    }

    suspend fun getClusterPolygon(clusterId: Int): GeoMultiPolygon? {
        if (!polygons.containsKey(clusterId)) {
            polygons[clusterId] = ClusterClient.getClusterPolygon(clusterId)
        }
        return polygons[clusterId]
    }

    suspend fun getInboundClusters(clusterId: Int): List<Int>? {
        if (!inbound.containsKey(clusterId)) {
            inbound[clusterId] = ClusterClient.getInboundClusters(clusterId)
        }
        return inbound[clusterId]
    }

    suspend fun getOutboundClusters(clusterId: Int): List<Int>? {
        if (!outbound.containsKey(clusterId)) {
            outbound[clusterId] = ClusterClient.getOutboundClusters(clusterId)
        }
        return outbound[clusterId]
    }
}