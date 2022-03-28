package io.joaofig.vedmap.databases

import io.joaofig.vedmap.models.Vehicle
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object VehicleTable : IntIdTable(columnName = "vehicle_id", name = "vehicle") {
    val vehicleType:    Column<String?> = text("vehicle_type", eagerLoading = true).nullable()
    val vehicleClass:   Column<String?> = text("vehicle_class", eagerLoading = true).nullable()
    val engine:         Column<String?> = text("engine", eagerLoading = true).nullable()
    val transmission:   Column<String?> = text("transmission", eagerLoading = true).nullable()
    val driveWheels:    Column<String?> = text("drive_wheels", eagerLoading = true).nullable()
    val weight:         Column<Double?> = double("weight").nullable()
}


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