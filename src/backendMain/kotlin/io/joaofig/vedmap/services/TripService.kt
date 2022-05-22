package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.Trajectory
import io.joaofig.vedmap.models.Trip
import io.joaofig.vedmap.repositories.TripRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
actual class TripService : ITripService {
    @Autowired
    private lateinit var repository: TripRepository

    override suspend fun getVehicleTripIds(vehicleId: Int): List<Int> {
        return repository.getTripList(vehicleId)
    }

    override suspend fun getVehicleTrips(vehicleId: Int): List<Trip> {
        return repository.getVehicleTrips(vehicleId)
    }

    override suspend fun getClusterTrips(clusterIni: Int, clusterEnd: Int): List<Trip> {
        return repository.getClusterTrips(clusterIni, clusterEnd)
    }

    override suspend fun getSingleVehicleTrajectory(tripId: Int): Trajectory {
        return repository.getSingleVehicleTrajectory(tripId)
    }
}