package io.joaofig.vedmap.views

import io.kvision.core.*
import io.kvision.form.select.simpleSelectInput
import io.kvision.html.Div
import io.kvision.html.div
import io.kvision.panel.vPanel
import io.kvision.utils.perc
import io.kvision.utils.px

class MainView constructor() : Div() {
    private val contentDiv = Div() {
        overflow = Overflow.AUTO
        height = 100.perc
    }

    init {
        position = Position.ABSOLUTE
        top = 0.px
        bottom = 0.px

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
