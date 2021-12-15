package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.Trip
import io.kvision.annotations.KVService

@KVService
interface ITripService {
    suspend fun getVehicleTripIds(vehicleId: Int): List<Int>
    suspend fun getVehicleTrips(vehicleId: Int): List<Trip>
}