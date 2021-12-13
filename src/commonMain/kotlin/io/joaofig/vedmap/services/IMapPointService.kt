package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.MapPoint
import io.kvision.annotations.KVService

@KVService
interface IMapPointService {
    suspend fun getMapPoints(): List<MapPoint>
    suspend fun getTripMapPoints(tripId: Int): List<MapPoint>
    suspend fun getVehicleMapPoints(vehicleId: Int): List<MapPoint>

}