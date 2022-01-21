package io.joaofig.vedmap

import io.joaofig.vedmap.views.MainView
import io.kvision.*
import io.kvision.i18n.DefaultI18nManager
import io.kvision.i18n.I18n
import io.kvision.maps.BaseLayerProvider
import io.kvision.maps.CRS
import io.kvision.maps.Maps
import io.kvision.panel.root
import io.kvision.utils.perc
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher

val AppScope = CoroutineScope(window.asCoroutineDispatcher())

class App : Application() {

    private fun registerTranslations() {
        I18n.manager =
            DefaultI18nManager(
                mapOf(
                    "en" to require("i18n/messages-en.json"),
                    "pl" to require("i18n/messages-pl.json")
                )
            )
    }

    private fun createMap(): Maps {
        val map = Maps(
            lat = 0.0,
            lng= 0.0,
            zoom = 4,
            baseLayerProvider = BaseLayerProvider.OSM,
            crs = CRS.EPSG3857)
        map.height = 100.perc
        map.width = 100.perc
        return map
    }

    override fun start(state: Map<String, Any>) {
        registerTranslations()

        root("kvapp") {
            add(MainView())
        }
//
//        AppScope.launch {
//            val trips = TripClient.getVehicleTrips(2)
//            for (trip in trips) {
//                root.add(Span(trip.toString()))
//                root.add(Br())
//            }
//        }
//
//        AppScope.launch {
//            val vehicles = VehicleClient.getVehicles()
//            for (vehicle in vehicles) {
//                root.add(Span(vehicle.toString()))
//                root.add(Br())
//            }
//        }

    }
}

fun main() {
    startApplication(
        ::App,
        module.hot,
        BootstrapModule,
        BootstrapCssModule,
        BootstrapSelectModule,
        BootstrapDatetimeModule,
        BootstrapSpinnerModule,
        BootstrapUploadModule,
        BootstrapTypeaheadModule,
        BootstrapIconsModule,
        FontAwesomeModule,
        RichTextModule,
        ChartModule,
        TabulatorModule,
        MapsModule,
        ToastModule,
        PrintModule,
        CoreModule
    )
}
