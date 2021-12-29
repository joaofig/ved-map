package io.joaofig.vedmap.views

import io.kvision.core.Overflow
import io.kvision.core.Position
import io.kvision.form.select.simpleSelectInput
import io.kvision.html.Div
import io.kvision.html.div
import io.kvision.panel.vPanel
import io.kvision.utils.perc
import io.kvision.utils.px

class MainView constructor() : Div() {
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
            ) {  }

            div {
                overflow = Overflow.AUTO
                height = 100.perc

                add(VehicleView())
            }
        }

    }
}
