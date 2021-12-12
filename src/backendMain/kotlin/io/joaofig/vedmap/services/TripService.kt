package io.joaofig.vedmap.services

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
actual class TripService : ITripService {
    override suspend fun getVehicleTripIds(vehicleId: Int): List<Int> {
        return listOf(1, 2, 3, 4, 5)
    }
}