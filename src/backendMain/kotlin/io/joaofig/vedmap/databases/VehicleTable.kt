package io.joaofig.vedmap.databases

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