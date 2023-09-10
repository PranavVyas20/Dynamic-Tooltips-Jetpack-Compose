package com.example.plotlineassignment.ui.components

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.plotlineassignment.data.TooltipPopupProperties
import com.example.plotlineassignment.provider.MyPopupPositionProvider

@Composable
fun TooltipPopup(
    modifier: Modifier,
    tooltipProperties: TooltipPopupProperties,
    anchor: @Composable (tooltipState: TooltipState) -> Unit,
    tooltipState: TooltipState,
) {
    val view =  LocalView.current.rootView
    var anchorOffsetPair by remember {
        mutableStateOf(Offset(0f, 0f) to 0f)
    }
    var arrowPositionOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    var calculatedAlignment by remember {
        mutableStateOf(TooltipAlignment.TOP)
    }
    Box(modifier = modifier.onGloballyPositioned {
        anchorOffsetPair = calculateTooltipPopupPosition(view, it)
    }) {
        val popupPositionProvider = remember(key1 = anchorOffsetPair) {
            MyPopupPositionProvider(
                alignment = tooltipProperties.alignment,
                viewHeight = view.height,
                arrowHeight = tooltipProperties.arrowHeight.value,
                anchorCenterPosX = anchorOffsetPair.first.x,
                centerPositionYWithStatusBar = anchorOffsetPair.second,
                centerPositionYWithoutStatusBar = anchorOffsetPair.first.y,
                onTooltipPosition = { tooltipPositionData ->
                    arrowPositionOffset = tooltipPositionData.arrowPositionOffset
                    calculatedAlignment = tooltipPositionData.alignment
                },
            )
        }
        anchor(tooltipState)
        if (tooltipState.isVisible) {
            Popup(
                onDismissRequest = {tooltipState.toggleVisibility()},
                popupPositionProvider = popupPositionProvider
            ) {
                BubbleLayout(
                    alignment = calculatedAlignment,
                    tooltipText = tooltipProperties.tooltipText,
                    textSize = tooltipProperties.textSize,
                    showImage = tooltipProperties.showImage,
                    tooltipTextColor = tooltipProperties.textColor,
                    arrowPositionOffset = arrowPositionOffset,
                    tooltipPadding = tooltipProperties.padding,
                    tooltipCornerRadius = tooltipProperties.cornerRadius,
                    tooltipBackgroundColor = tooltipProperties.backgroundColor,
                    arrowHeight = tooltipProperties.arrowHeight
                )
            }
        }
    }

}

private fun calculateTooltipPopupPosition(
    view: View,
    coordinates: LayoutCoordinates?,
): Pair<Offset, Float> {
    coordinates ?: return Offset(0f, 0f) to 0f
    val visibleWindowBounds = android.graphics.Rect()
    view.getWindowVisibleDisplayFrame(visibleWindowBounds)

    val boundsInWindow = coordinates.boundsInWindow()
    val centerPositionX = boundsInWindow.right - (boundsInWindow.right - boundsInWindow.left) / 2
    val height = boundsInWindow.bottom - boundsInWindow.top
    val centerPositionYWithStatusBar = boundsInWindow.top + (height / 2)
    val centerPositionYWithoutStatusBar = coordinates.positionInRoot().y + height / 2

    return Offset(centerPositionX, centerPositionYWithoutStatusBar) to centerPositionYWithStatusBar
}
data class TooltipPositionData(
    val alignment: TooltipAlignment,
    val arrowPositionOffset: Offset
)
class TooltipState internal constructor(initialTooltipVisibility: Boolean) {
    private var isVisibleState by mutableStateOf(initialTooltipVisibility)
    val isVisible get() = isVisibleState
    fun toggleVisibility() {
        isVisibleState = isVisibleState.not()
    }
}

enum class TooltipAlignment(val key: String) {
    TOP("Top"), BOTTOM("Bottom"), START("Start"), END("End");

    companion object {
        fun get(key: String): TooltipAlignment? = TooltipAlignment.values().firstOrNull {
            it.key == key
        }
    }

}

@Composable
fun rememberTooltipState(initiallyTooltipVisible: Boolean): TooltipState {
    return remember {
        TooltipState(initiallyTooltipVisible)
    }
}
@Preview
@Composable
fun TooltipPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        TooltipPopup(
            modifier = Modifier,
            tooltipProperties = TooltipPopupProperties(
                alignment = TooltipAlignment.START,
                textSize = 14.sp,
                textColor = Color.White,
                backgroundColor = Color.Black,
                padding = 8.dp,
                cornerRadius = 6.dp,
                arrowHeight = 25.dp,
                showImage = false,
                tooltipText = "Some long  sldjfsdgsdgsdg \n sdifljs \n sdfsldjf"

            ),
            anchor = { tooltipState ->
                Button(onClick = { tooltipState.toggleVisibility() }) {
                    Text("Button")
                }
            },
            tooltipState = rememberTooltipState(initiallyTooltipVisible = false)
        )
    }
}