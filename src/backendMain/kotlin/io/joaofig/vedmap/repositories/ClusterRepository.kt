package io.joaofig.vedmap.repositories

import io.joaofig.vedmap.databases.ClusterPointRow
import io.joaofig.vedmap.databases.ClusterRow
import io.joaofig.vedmap.databases.VehicleEnergyDatabase
import io.joaofig.vedmap.models.GeoBounds
import io.joaofig.vedmap.models.Cluster
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class ClusterRepository {
    private val db = VehicleEnergyDatabase.db

    fun getMapBoundaries(): GeoBounds {
        return transaction(db = db) {
            val minLat = ClusterPointRow.all().minOf { it.latitude }
            val maxLat = ClusterPointRow.all().maxOf { it.latitude }
            val minLon = ClusterPointRow.all().minOf { it.longitude }
            val maxLon = ClusterPointRow.all().maxOf { it.longitude }

            GeoBounds(minLat, maxLat, minLon, maxLon)
        }
    }

    fun getClusters(): List<Cluster> {
        return transaction(db = db) {
            ClusterRow.all().map {
                Cluster(it.id.value, it.name)
            }
        }
    }
}