package io.joaofig.vedmap.repositories

import com.uber.h3core.H3Core
import io.joaofig.vedmap.databases.ClusterPointRow
import io.joaofig.vedmap.databases.ClusterPointTable
import io.joaofig.vedmap.databases.ClusterRow
import io.joaofig.vedmap.databases.VehicleEnergyDatabase
import io.joaofig.vedmap.models.*
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

    fun getClusterPolygon(clusterId: Int): GeoMultiPolygon {
        val h3 = H3Core.newInstance()
        val hexagons = transaction(db = db) {
            ClusterPointRow.find {
                ClusterPointTable.clusterId eq clusterId
            }.map {
                it.h3
            }.distinct()
        }

        val multiList = h3.h3AddressSetToMultiPolygon(hexagons, true)
        val geoMultiPolygon = GeoMultiPolygon(
            multiList.map { polygon ->
                GeoPolygon(
                    polygon.map { ring ->
                        GeoPolyline(
                            ring.map { l ->
                                GeoLocation(l.lat, l.lng)
                            }
                        )
                    }
                )
            }
        )
        return geoMultiPolygon
    }
}