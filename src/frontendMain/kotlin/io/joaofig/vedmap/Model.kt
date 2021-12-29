package io.joaofig.vedmap

import io.joaofig.vedmap.clients.VehicleClient
import io.joaofig.vedmap.models.TripModel
import io.joaofig.vedmap.models.VehicleModel
import io.kvision.state.ObservableValue
import kotlinx.coroutines.launch

object Model {
    val trips = TripModel()
    val vehicles = ObservableValue(VehicleModel())

    init {
        AppScope.launch {
            vehicles.value = VehicleModel(VehicleClient.getVehicles())
        }
    }

    private val pingService = PingService()

    suspend fun ping(message: String): String {
        return pingService.ping(message)
    }

}
