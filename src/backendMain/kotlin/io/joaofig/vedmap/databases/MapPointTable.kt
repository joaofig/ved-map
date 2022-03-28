package io.joaofig.vedmap.databases

import io.joaofig.vedmap.models.MapPoint
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object MapPointTable: IntIdTable(name = "signal", columnName = "signal_id") {
    val dayNumber:      Column<Double> = double("day_num")
    val vehicleId:      Column<Int> = integer("vehicle_id")
    val tripId:         Column<Int> = integer("trip_id")
    val timestamp:      Column<Int> = integer("timestamp")
    val latitude:       Column<Double> = double("latitude")
    val longitude:      Column<Double> = double("longitude")
    val speed:          Column<Double?> = double("speed").nullable()
    val maf:            Column<Double?> = double("maf").nullable()
    val rpm:            Column<Double?> = double("rpm").nullable()
    val absoluteLoad:   Column<Double?> = double("abs_load").nullable()
}

class MapPointRow(id: EntityID<Int>): IntEntity(id) {
    var dayNumber by MapPointTable.dayNumber
    var vehicleId by MapPointTable.vehicleId
    var tripId by MapPointTable.tripId
    var timestamp by MapPointTable.timestamp
    var latitude by MapPointTable.latitude
    var longitude by MapPointTable.longitude
    var speed by MapPointTable.speed
    var maf by MapPointTable.maf
    var rpm by MapPointTable.rpm
    var absoluteLoad by MapPointTable.absoluteLoad

    fun toMapPoint(): MapPoint {
        return MapPoint(
            id = id.value,
            dayNumber = dayNumber,
            vehicleId = vehicleId,
            tripId = tripId,
            timestamp = timestamp,
            latitude = latitude,
            longitude = longitude,
            speed = speed,
            maf = maf,
            rpm = rpm,
            absoluteLoad = absoluteLoad
        )
    }
}
