package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.Vehicle
import io.kvision.annotations.KVService

@KVService
interface IVehicleService {
    suspend fun getVehicles(): List<Vehicle>
}