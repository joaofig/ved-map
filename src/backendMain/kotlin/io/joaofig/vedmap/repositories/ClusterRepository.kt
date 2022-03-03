package io.joaofig.vedmap.repositories

import io.joaofig.vedmap.databases.ClusterPointRow
import io.joaofig.vedmap.databases.TripRow.Companion.all
import io.joaofig.vedmap.databases.VehicleEnergyDatabase
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class ClusterRepository {
    private val db = VehicleEnergyDatabase.db

    fun getMapBoundaries(): Unit {
        transaction(db = db) {
//            ClusterPointRow
        }
    }
}