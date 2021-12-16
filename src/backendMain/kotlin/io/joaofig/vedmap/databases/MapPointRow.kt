package io.joaofig.vedmap.databases

import io.joaofig.vedmap.models.MapPoint
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

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