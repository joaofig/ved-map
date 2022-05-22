package io.joaofig.vedmap.views

import io.joaofig.vedmap.controls.duoCheckBox
import io.joaofig.vedmap.controls.sortHeaderCell
import io.joaofig.vedmap.controls.triCheckBox
import io.joaofig.vedmap.viewmodels.ViewModelHub
import io.kvision.core.Overflow
import io.kvision.core.Position
import io.kvision.core.Style
import io.kvision.core.onEvent
import io.kvision.form.text.TextInputType
import io.kvision.form.text.text
import io.kvision.html.Div
import io.kvision.html.div
import io.kvision.i18n.I18n
import io.kvision.panel.vPanel
import io.kvision.state.bind
import io.kvision.table.*
import io.kvision.utils.perc
import io.kvision.utils.pt
import io.kvision.utils.px
import io.kvision.utils.vh

class ClusterListView : Div() {
    private val viewModel = ViewModelHub.clusterList

    init {
        padding = 4.px
        height = 100.perc

        add(
            vPanel {
                overflow = Overflow.AUTO
                height = 100.perc

                text(TextInputType.SEARCH) {
                    placeholder = I18n.tr("Search ...")
                    onEvent {
                        input = {
                            viewModel.nameFilter = value ?: ""
                        }
                    }
                }
                div{
                    overflow = Overflow.AUTO
                    height = 100.vh
                }.bind(viewModel.clusters) { state ->
                    val headerStyle = Style(selector = "thead th") {
                        position = Position.STICKY
                        top = 0.px
                        }
                    table(
                        types = setOf(TableType.STRIPED, TableType.HOVER),
                        theadColor = TableColor.PRIMARY) {
                        addCssStyle(headerStyle)
                        fontSize = 10.pt

                        headerCell("Id")
                        sortHeaderCell("Name ", this@ClusterListView.viewModel.sortAscending)
                        headerCell {
                            triCheckBox(this@ClusterListView.viewModel.allSelected)
                        }

                        state.mapIndexed { index, cluster ->
                            index to cluster
                        }.forEach {
                            (_, state) -> row {
                                cell(state.cluster.id.toString())
                                cell(state.cluster.name)
                                cell {
                                   duoCheckBox(state.isSelected)
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}