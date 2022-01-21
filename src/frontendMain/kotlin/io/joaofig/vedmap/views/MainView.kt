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
                    options = listOf("vehicles" to "Vehicles"),
                    value = "vehicles"
                ) {  }.onEvent {
                    change = {
                        selectView(self.value)
                    }
                }

                add(contentDiv)
                selectView("vehicles")
            }

            add(MapView())
        }


    }

    private fun setView(view: Component) {
        contentDiv.removeAll()
        contentDiv.add(view)
    }

    private fun selectView(viewName: String?) {
        if (viewName != null) {
            when (viewName) {
                "vehicles" -> setView(VehicleView())
            }
        }
    }
}
