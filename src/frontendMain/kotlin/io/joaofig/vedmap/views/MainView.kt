package io.joaofig.vedmap.views

import io.kvision.core.Component
import io.kvision.core.Overflow
import io.kvision.core.onEvent
import io.kvision.form.select.simpleSelectInput
import io.kvision.html.Div
import io.kvision.panel.splitPanel
import io.kvision.panel.vPanel
import io.kvision.utils.perc
import io.kvision.utils.vh

class MainView constructor() : Div() {
    private val contentDiv = Div() {
        overflow = Overflow.AUTO
        height = 100.vh
    }

    private var vehicleView: VehicleView? = null
    private var clusterListView: ClusterListView? = null
    private var tripView: TripView? = null

    init {
        height = 100.vh
        width = 100.perc

        splitPanel {
            height = 100.perc
            width = 100.perc

            vPanel {
                overflow = Overflow.AUTO
                height = 100.perc

                simpleSelectInput(
                    options = listOf(
                        "clusters" to "Clusters",
                        "trips" to "Trips",
                    ),
                    value = "clusters"
                ) {  }.onEvent {
                    change = {
                        selectView(self.value)
                    }
                }

                add(contentDiv)
                selectView("clusters")
            }
            add(MapView())
        }
    }

    private fun setView(view: Component) {
        contentDiv.removeAll()
        contentDiv.add(view)
    }

    private fun getVehicleView(): VehicleView {
        val view: VehicleView = vehicleView ?: VehicleView()
        vehicleView = view
        return view
    }

    private fun getClusterListView(): ClusterListView {
        val view: ClusterListView = clusterListView ?: ClusterListView()
        clusterListView = view
        return view
    }

    private fun getTripView(): TripView {
        val view: TripView = tripView ?: TripView()
        tripView = view
        return view
    }

    private fun selectView(viewName: String?) {
        if (viewName != null) {
            when (viewName) {
                "trips" -> setView(getTripView())
                "clusters" -> setView(getClusterListView())
            }
        }
    }
}
