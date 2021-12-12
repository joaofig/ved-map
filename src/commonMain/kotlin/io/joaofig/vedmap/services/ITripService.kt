package io.joaofig.vedmap.services

import io.kvision.annotations.KVService

@KVService
interface ITripService {
    suspend fun getVehicleTripIds(vehicleId: Int): List<Int>
}