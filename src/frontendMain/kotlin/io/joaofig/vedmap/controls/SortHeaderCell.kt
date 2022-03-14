package io.joaofig.vedmap.controls

import io.kvision.core.Cursor
import io.kvision.state.ObservableValue
import io.kvision.table.HeaderCell


fun HeaderCell.sortGlyph(bind: ObservableValue<Boolean?>) {
    val sortUp = "▲"
    val sortDn = "▼"

    cursor = Cursor.POINTER

    val contentText = this.content ?: ""
    val headerText = if (contentText.endsWith(sortUp) || contentText.endsWith(sortDn)) {
        contentText.take(contentText.length - 1)
    } else {
        contentText
    }

    if (bind.value == null) {
        content = headerText
    } else {
        if (bind.value == true) {
            content = headerText + sortUp
        } else {
            content = headerText + sortDn
        }
    }
}

fun HeaderCell.sortClick(bind: ObservableValue<Boolean?>) {
    val sortUp = "▲"
    val sortDn = "▼"

    val contentText = this.content ?: ""
    val headerText = if (contentText.endsWith(sortUp) || contentText.endsWith(sortDn)) {
        contentText.take(contentText.length - 1)
    } else {
        contentText
    }

    if (bind.value == null) {
        content = headerText + sortUp
        bind.value = true
    } else {
        if (bind.value == true) {
            content = headerText + sortDn
            bind.value = false
        } else {
            content = headerText
            bind.value = null
        }
    }
}