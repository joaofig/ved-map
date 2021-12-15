package io.joaofig.vedmap.clients

import io.joaofig.vedmap.models.Vehicle
import io.joaofig.vedmap.services.VehicleService

object VehicleClient {
    private val service = VehicleService()

    suspend fun getVehicles(): List<Vehicle> = service.getVehicles()
}