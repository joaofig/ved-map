package io.joaofig.vedmap.views

import io.joaofig.vedmap.Model
import io.kvision.core.Position
import io.kvision.core.Style
import io.kvision.html.Div
import io.kvision.state.bind
import io.kvision.table.*
import io.kvision.utils.perc
import io.kvision.utils.px

class VehicleView : Div() {
    init {
        padding = 4.px
        height = 100.perc

        bind(Model.vehicles) { state ->
            val headerStyle = Style(selector = "thead th") {
                position = Position.STICKY
                top = 0.px
            }
            table(types = setOf(TableType.STRIPED, TableType.HOVER)) {
                addCssStyle(headerStyle)

                headerCell("Id") { }
                headerCell("Type") { }
                headerCell("Class") { }
                headerCell("Engine") { }
                headerCell("Transmission") { }
                headerCell("Drive") { }
                headerCell("Weight") { }

                state.vehicles.mapIndexed { index, vehicle -> index to vehicle }
                    .forEach { (_, vehicle) ->
                        row {
                            cell(vehicle.id.toString())
                            cell(vehicle.vehicleType)
                            cell(vehicle.vehicleClass)
                            cell(vehicle.engine)
                            cell(vehicle.transmission)
                            cell(vehicle.driveWheels)
                            cell(vehicle.weight.toString())
                        }
                    }
            }
        }
    }
}