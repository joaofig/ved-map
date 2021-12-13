package io.joaofig.vedmap.services

import io.joaofig.vedmap.models.MapPoint

actual class MapPointService: IMapPointService  {
    override suspend fun getMapPoints(): List<MapPoint> {
        return listOf()
    }

    override suspend fun getTripMapPoints(tripId: Int): List<MapPoint> {
        return listOf()
    }

    override suspend fun getVehicleMapPoints(vehicleId: Int): List<MapPoint> {
        return listOf()
    }
}