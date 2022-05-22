package io.joaofig.vedmap.controls

import io.kvision.core.Container
import io.kvision.html.Div
import io.kvision.html.span
import io.kvision.utils.pt

class StaticRadioButton(state: Boolean) : Div() {
    init {
        val iconName = if (state) {
            "\uf192" //"\uf058"
        } else {
            "\uf111"
        }
        span(iconName, className = "far") {
            fontSize = 12.pt
        }
    }
}


fun Container.staticRadioButton(state: Boolean): StaticRadioButton {
    val radioButton = StaticRadioButton(state)
    this.add(radioButton)
    return radioButton
}
