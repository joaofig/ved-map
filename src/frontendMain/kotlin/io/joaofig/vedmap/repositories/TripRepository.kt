package io.joaofig.vedmap.repositories

import io.joaofig.vedmap.clients.TripClient
import io.joaofig.vedmap.models.Trajectory
import io.joaofig.vedmap.models.Trip

object TripRepository {
    private val trips = mutableMapOf<Int, Trip>()
    private val clusterTrips = mutableMapOf<Pair<Int,Int>, List<Trip>>()
    private val trajectories = mutableMapOf<Int, Trajectory>()

    suspend fun getClusterTrips(clusterIni: Int, clusterEnd: Int): List<Trip>? {
        val key = Pair<Int, Int>(clusterIni, clusterEnd)

        if (!clusterTrips.containsKey(key)) {
            clusterTrips[key] = TripClient.getClusterTrips(clusterIni, clusterEnd)
        }
        return clusterTrips[key]
    }

    suspend fun getSingleVehicleTrip(trip: Trip): Trajectory? {
        if (!trajectories.containsKey(trip.id)) {
            trajectories[trip.id] = TripClient.getSingleVehicleTrajectory(trip.id)
        }
        return trajectories[trip.id]
    }
}