package com.example.plotlineassignment.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun TooltipPopup(
    modifier: Modifier = Modifier,
    anchor: @Composable (tooltipState: TooltipState) -> Unit,
    tooltipState: TooltipState,
    alignment: TooltipAlignment
) {
    var tooltipContentSize by remember {
        mutableStateOf(Pair(0, 0))
    }
    var offset by remember {
        mutableStateOf(IntOffset(0, 0))
    }
    var anchorSpaceStart by remember {
        mutableStateOf(0f)
    }
    var anchorSpaceEnd by remember {
        mutableStateOf(0f)
    }
    var anchorSpaceTop by remember {
        mutableStateOf(0f)
    }
    var anchorSpaceBottom by remember {
        mutableStateOf(0f)
    }
    val configuration = LocalConfiguration.current

    val screenWidth = remember {
        configuration.screenWidthDp
    }

    var arrowAlignment by remember {
        mutableStateOf(TooltipAlignment.TOP)
    }

    Box(modifier = modifier) {
        Box(modifier = Modifier.onGloballyPositioned { coordinates ->
            anchorSpaceStart = coordinates.positionInRoot().x
            anchorSpaceEnd = screenWidth - (coordinates.size.width + anchorSpaceStart)
        }) {
            anchor(tooltipState)
        }
        if (tooltipState.isVisible) {
            Popup(
                onDismissRequest = { tooltipState.toggleVisibility() },
                properties = PopupProperties(dismissOnClickOutside = true),
                alignment = when (alignment) {
                    TooltipAlignment.TOP -> {
                        arrowAlignment = TooltipAlignment.BOTTOM
                        offset = IntOffset(x = 0, y = -tooltipContentSize.first)
                        Alignment.TopCenter
                    }

                    TooltipAlignment.BOTTOM -> {
                        arrowAlignment = TooltipAlignment.TOP
                        offset = IntOffset(x = 0, y = tooltipContentSize.first)
                        Alignment.BottomCenter
                    }

                    TooltipAlignment.START -> {
                        // If there is not enough space between anchor's start and the screen, try to show the tooltip on the anchor's end
                        if (anchorSpaceStart < tooltipContentSize.second) {
                            arrowAlignment = TooltipAlignment.START
                            offset = IntOffset(x = tooltipContentSize.second, y = 0)
                            Alignment.CenterEnd
                        } else {
                            arrowAlignment = TooltipAlignment.END
                            offset = IntOffset(x = -tooltipContentSize.second, y = 0)
                            Alignment.CenterStart
                        }
                    }

                    TooltipAlignment.END -> {
                        if (anchorSpaceEnd < tooltipContentSize.second) {
                            arrowAlignment = TooltipAlignment.END
                            offset = IntOffset(x = -tooltipContentSize.second, y = 0)
                            Alignment.CenterStart
                        } else {
                            arrowAlignment = TooltipAlignment.START
                            offset = IntOffset(x = tooltipContentSize.second, y = 0)
                            Alignment.CenterEnd
                        }

                    }
                },
                offset = offset
            ) {
                Tooltip(
                    arrowAlignment = arrowAlignment,
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        tooltipContentSize = coordinates.size.height to coordinates.size.width
                    })
            }
        }
    }
}

@Preview
@Composable
fun TooltipDemo() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        TooltipPopup(
            tooltipState = rememberTooltipState(initiallyTooltipVisible = false),
            anchor = { tooltipState ->
                Button(onClick = { tooltipState.toggleVisibility() }) {
                    Text(text = "Button 1")
                }
            },
            alignment = TooltipAlignment.TOP,
        )
    }
}

class TooltipState internal constructor(private var initialTooltipVisibility: Boolean) {
    private var isVisibleState by mutableStateOf(initialTooltipVisibility)
    val isVisible get() = isVisibleState
    fun toggleVisibility() {
        isVisibleState = isVisibleState.not()
    }
}

enum class TooltipAlignment {
    TOP,
    BOTTOM,
    START,
    END
}

@Composable
fun rememberTooltipState(initiallyTooltipVisible: Boolean): TooltipState {
    return remember {
        TooltipState(initiallyTooltipVisible)
    }
}