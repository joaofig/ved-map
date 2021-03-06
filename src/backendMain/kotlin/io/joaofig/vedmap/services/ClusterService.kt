package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.models.GeoBounds
import io.joaofig.vedmap.models.GeoLocation
import io.joaofig.vedmap.models.GeoMultiPolygon
import io.joaofig.vedmap.repositories.ClusterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
actual class ClusterService : IClusterService {
    @Autowired
    private lateinit var repository: ClusterRepository

    override suspend fun getClusterBounds(): GeoBounds {
        return repository.getMapBoundaries()
    }

    override suspend fun getClusters(): List<Cluster> {
        return repository.getClusters()
    }

    override suspend fun getClusterPolygon(clusterId: Int): GeoMultiPolygon {
        return repository.getClusterPolygon(clusterId)
    }

    override suspend fun getInboundClusters(clusterId: Int): List<Int> {
        val clusters = repository.getInboundClusters(clusterId)
        println("#cluster: ${clusters.count()}")
        return clusters
    }

    override suspend fun getOutboundClusters(clusterId: Int): List<Int> {
        return repository.getOutboundClusters(clusterId)
    }

    override suspend fun getClusterPoints(clusterId: Int): List<GeoLocation> {
        return repository.getClusterPoints(clusterId)
    }
}