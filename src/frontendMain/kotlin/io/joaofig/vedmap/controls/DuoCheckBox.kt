package io.joaofig.vedmap.controls

import io.kvision.core.Container
import io.kvision.core.Cursor
import io.kvision.core.onClick
import io.kvision.html.Div
import io.kvision.html.span
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import io.kvision.utils.pt

class DuoCheckBox(private val binder: ObservableValue<Boolean>) : Div() {
    init {
        bind(binder) { state ->
            val iconName = if (state) {
                "\uf14a"
            } else {
                "\uf0c8"
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

fun Container.duoCheckBox(binder: ObservableValue<Boolean>): DuoCheckBox {
    val duoCheckBox = DuoCheckBox(binder)
    this.add(duoCheckBox)
    return duoCheckBox
}
