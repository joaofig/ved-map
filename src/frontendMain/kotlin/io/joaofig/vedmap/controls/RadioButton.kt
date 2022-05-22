package io.joaofig.vedmap.controls

import io.kvision.core.Container
import io.kvision.core.Cursor
import io.kvision.core.onClick
import io.kvision.html.Div
import io.kvision.html.span
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import io.kvision.utils.pt

class RadioButton(private val binder: ObservableValue<Boolean>) : Div() {
    init {
        bind(binder) { state ->
            val iconName = if (state) {
                "\uf192" //"\uf058"
            } else {
                "\uf111"
            }
            span(iconName, className = "far") {
                cursor = Cursor.POINTER
                fontSize = 12.pt
            }
        }

        onClick {
            binder.value = !binder.value
        }
    }
}

fun Container.radioButton(binder: ObservableValue<Boolean>): RadioButton {
    val radioButton = RadioButton(binder)
    this.add(radioButton)
    return radioButton
}
