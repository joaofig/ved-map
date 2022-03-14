package io.joaofig.vedmap.views

import io.joaofig.vedmap.Model
import io.joaofig.vedmap.controls.dualCheckBox
import io.joaofig.vedmap.controls.sortClick
import io.joaofig.vedmap.controls.sortGlyph
import io.kvision.core.*
import io.kvision.form.text.TextInputType
import io.kvision.form.text.text
import io.kvision.html.Div
import io.kvision.html.div
import io.kvision.html.icon
import io.kvision.i18n.I18n
import io.kvision.panel.vPanel
import io.kvision.state.bind
import io.kvision.table.*
import io.kvision.utils.perc
import io.kvision.utils.pt
import io.kvision.utils.px
import io.kvision.utils.vh

class ClusterListView : Div() {
    init {
        padding = 4.px
        height = 100.perc

        Model.loadClusters()

        add(
            vPanel {
                overflow = Overflow.AUTO
                height = 100.perc

                text(TextInputType.SEARCH) {
                    placeholder = I18n.tr("Search ...")
                    onEvent {
                        input = {
                            Model.clusterListModel.nameFilter = value ?: ""
                        }
                    }
                }
                div{
                    overflow = Overflow.AUTO
                    height = 100.vh
                }.bind(Model.clusterListModel.clusters) { state ->
                    val headerStyle = Style(selector = "thead th") {
                        position = Position.STICKY
                        top = 0.px
                    }
                    table(types = setOf(TableType.STRIPED, TableType.HOVER)) {
                        addCssStyle(headerStyle)
                        fontSize = 10.pt

                        headerCell("Id") { }
                        headerCell("Name ") {
                            sortGlyph(Model.clusterListModel.sortAscending)
                        }.onClick {
                            sortClick(Model.clusterListModel.sortAscending)
                        }
                        headerCell {
                            icon("far fa-square")
                        }

                        state.mapIndexed {
                                index, cluster -> index to cluster
                        }.forEach {
                            (_, cluster) -> row {
                                cell(cluster.id.toString())
                                cell(cluster.name)
                                cell {
                                   dualCheckBox(cluster.isSelected)
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}