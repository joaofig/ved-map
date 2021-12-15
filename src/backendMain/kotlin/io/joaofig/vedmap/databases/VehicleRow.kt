package io.joaofig.vedmap.databases

import io.joaofig.vedmap.models.Vehicle
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class VehicleRow(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<VehicleRow>(VehicleTable)

    var vehicleType     by VehicleTable.vehicleType
    var vehicleClass    by VehicleTable.vehicleClass
    var engine          by VehicleTable.engine
    var transmission    by VehicleTable.transmission
    var driveWheels     by VehicleTable.driveWheels
    var weight          by VehicleTable.weight

    fun toVehicle(): Vehicle {
        return Vehicle(
            id              = id.value,
            vehicleType     = vehicleType,
            vehicleClass    = vehicleClass,
            engine          = engine,
            transmission    = transmission,
            driveWheels     = driveWheels,
            weight          = weight
        )
    }
}