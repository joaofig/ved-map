package io.joaofig.vedmap.clients

import io.joaofig.vedmap.models.Trajectory
import io.joaofig.vedmap.models.Trip
import io.joaofig.vedmap.services.TripService

object TripClient {
    private val service = TripService()

    suspend fun getVehicleTripIds(vehicleId: Int): List<Int> {
        return service.getVehicleTripIds(vehicleId)
    }

    suspend fun getVehicleTrips(vehicleId: Int): List<Trip> {
        return service.getVehicleTrips(vehicleId)
    }

    suspend fun getClusterTrips(clusterIni: Int, clusterEnd: Int): List<Trip> {
        return service.getClusterTrips(clusterIni, clusterEnd)
    }

    suspend fun getSingleVehicleTrajectory(tripId: Int): Trajectory {
        return service.getSingleVehicleTrajectory(tripId)
    }
}