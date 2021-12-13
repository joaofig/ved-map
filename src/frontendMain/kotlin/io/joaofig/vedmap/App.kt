package io.joaofig.vedmap

import io.kvision.Application
import io.kvision.CoreModule
import io.kvision.BootstrapModule
import io.kvision.BootstrapCssModule
import io.kvision.BootstrapSelectModule
import io.kvision.BootstrapDatetimeModule
import io.kvision.BootstrapSpinnerModule
import io.kvision.BootstrapUploadModule
import io.kvision.BootstrapTypeaheadModule
import io.kvision.BootstrapIconsModule
import io.kvision.FontAwesomeModule
import io.kvision.RichTextModule
import io.kvision.ChartModule
import io.kvision.TabulatorModule
import io.kvision.MapsModule
import io.kvision.ToastModule
import io.kvision.PrintModule
import io.kvision.html.Span
import io.kvision.i18n.DefaultI18nManager
import io.kvision.i18n.I18n
import io.kvision.module
import io.kvision.panel.root
import io.kvision.require
import io.kvision.startApplication
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch

val AppScope = CoroutineScope(window.asCoroutineDispatcher())

class App : Application() {

    override fun start(state: Map<String, Any>) {
//        I18n.manager =
//            DefaultI18nManager(
//                mapOf(
//                    "en" to require("i18n/messages-en.json"),
//                    "pl" to require("i18n/messages-pl.json")
//                )
//            )

        val root = root("kvapp") {
        }
        AppScope.launch {
            val pingResult = Model.ping("Hello world from client!")
            root.add(Span(pingResult))
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
        ToastModule,
        PrintModule,
        CoreModule
    )
}
