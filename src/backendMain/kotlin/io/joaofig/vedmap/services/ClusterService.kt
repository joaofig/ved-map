package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.GeoBounds
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
}