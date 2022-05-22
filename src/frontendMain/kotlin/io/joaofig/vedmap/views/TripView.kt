package io.joaofig.vedmap.views

import io.joaofig.vedmap.controls.sortHeaderCell
import io.joaofig.vedmap.controls.staticRadioButton
import io.joaofig.vedmap.viewmodels.ViewModelHub
import io.kvision.core.*
import io.kvision.form.text.TextInputType
import io.kvision.form.text.text
import io.kvision.html.Div
import io.kvision.html.div
import io.kvision.i18n.I18n
import io.kvision.panel.Direction
import io.kvision.panel.splitPanel
import io.kvision.panel.vPanel
import io.kvision.state.bind
import io.kvision.table.*
import io.kvision.utils.perc
import io.kvision.utils.pt
import io.kvision.utils.px

class TripView : Div() {
    private val viewModel = ViewModelHub.trip

    init {
        padding = 4.px
        height = 100.perc

        add(
            splitPanel(direction = Direction.HORIZONTAL) {
                marginTop = 4.px
                height = 100.perc

                // Top Panel
                vPanel {
                    overflow = Overflow.AUTO
                    height = 50.perc

                    text(TextInputType.SEARCH) {
                        placeholder = I18n.tr("Search ...")
                        onEvent {
                            input = {
                                viewModel.clusterFilter = value ?: ""
                            }
                        }
                    }
                    div{
                        overflow = Overflow.AUTO
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
                            sortHeaderCell("Name ", this@TripView.viewModel.sortAscending)
                            headerCell {
                            }

                            state.mapIndexed { index, cluster ->
                                index to cluster
                            }.forEach { (_, state) ->
                                row {
                                    cursor = Cursor.POINTER

                                    cell(state.cluster.id.toString())
                                    cell(state.cluster.name)
                                    cell {
                                        staticRadioButton(this@TripView.viewModel.isClusterSelected(state))
                                    }
                                }.onClick {
                                    this@TripView.viewModel.selectedCluster.value = state
                                }
                            }
                        }
                    }
                }

                // Bottom Panel
                vPanel {
                    marginTop = 4.px
                    overflow = Overflow.AUTO
                    height = 50.perc

                    text(TextInputType.SEARCH) {
                        placeholder = I18n.tr("Search ...")
                        onEvent {
                            input = {
                                viewModel.childrenFilter = value ?: ""
                            }
                        }
                    }
                    div{
                        overflow = Overflow.AUTO
                    }.bind(viewModel.children) { state ->
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
                            sortHeaderCell("Name ", this@TripView.viewModel.sortAscending)
                            headerCell {
                            }

                            state.mapIndexed { index, cluster ->
                                index to cluster
                            }.forEach { (_, state) ->
                                row {
                                    cursor = Cursor.POINTER

                                    cell(state.cluster.id.toString())
                                    cell(state.cluster.name)
                                    cell {
                                        staticRadioButton(this@TripView.viewModel.isChildSelected(state))
                                    }
                                }.onClick {
                                    this@TripView.viewModel.selectedChild.value = state
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}