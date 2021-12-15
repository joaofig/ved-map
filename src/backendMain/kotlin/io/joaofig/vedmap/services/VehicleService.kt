package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.Vehicle
import io.joaofig.vedmap.repositories.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
actual class VehicleService : IVehicleService {
    @Autowired
    private lateinit var repository : VehicleRepository

    override suspend fun getVehicles(): List<Vehicle> {
        return repository.getVehicles()
    }
}