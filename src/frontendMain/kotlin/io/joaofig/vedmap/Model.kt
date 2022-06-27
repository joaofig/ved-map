package io.joaofig.vedmap

import io.joaofig.vedmap.models.TripModel
import io.joaofig.vedmap.models.VehicleModel
import io.kvision.state.ObservableValue

object Model {
    val trips = TripModel()
    val vehicles = ObservableValue(VehicleModel())

    init {
    }
}
