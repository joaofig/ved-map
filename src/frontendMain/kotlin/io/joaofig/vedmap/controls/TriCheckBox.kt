package io.joaofig.vedmap.controls

import io.kvision.core.Container
import io.kvision.core.Cursor
import io.kvision.core.onClick
import io.kvision.html.Div
import io.kvision.html.span
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import io.kvision.utils.pt

class TriCheckBox(private val binder: ObservableValue<Boolean?>) : Div()  {

    init {
        bind(binder) { state ->
            val iconName = when {
                state == null -> { "\uf146" }
                state -> { "\uf14a" }
                else -> { "\uf0c8" }
            }
            span(iconName, className = "far") {
                cursor = Cursor.POINTER
                fontSize = 12.pt
            }
        }

        onClick {
            binder.value = when (binder.value) {
                null -> false
                false -> true
                else -> false
            }
        }
    }
}

fun Container.triCheckBox(binder: ObservableValue<Boolean?>): TriCheckBox {
    val triCheckBox = TriCheckBox(binder)
    this.add(triCheckBox)
    return triCheckBox
}
