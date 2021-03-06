package io.joaofig.vedmap

import io.joaofig.vedmap.controls.MapContextMenuModule
import io.joaofig.vedmap.views.MainView
import io.kvision.*
import io.kvision.i18n.DefaultI18nManager
import io.kvision.i18n.I18n
import io.kvision.panel.root
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

    override fun start(state: Map<String, Any>) {
        registerTranslations()

        root("kvapp") {
            add(MainView())
        }
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
        MapContextMenuModule,
        ToastModule,
        PrintModule,
        CoreModule
    )
}
