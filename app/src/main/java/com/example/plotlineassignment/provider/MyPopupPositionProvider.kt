package com.example.plotlineassignment.provider

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.center
import androidx.compose.ui.window.PopupPositionProvider
import com.example.plotlineassignment.ui.components.TooltipAlignment
import com.example.plotlineassignment.ui.components.TooltipPositionData

class MyPopupPositionProvider(
    val alignment: TooltipAlignment,
    val viewHeight: Int,
    val onTooltipPosition: (tooltipPositionData: TooltipPositionData) -> Unit,
    val anchorCenterPosX: Float,
    val centerPositionYWithStatusBar: Float,
    val centerPositionYWithoutStatusBar: Float,
    val arrowHeight: Float,
) : PopupPositionProvider {

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        var popupPositionOffset = IntOffset(0, 0)
        var newAlignment = alignment

        val anchorHeight = anchorBounds.bottom - anchorBounds.top
        val anchorWidth = anchorBounds.right - anchorBounds.left

        val rightSpaceFromAnchorMid = windowSize.width - anchorCenterPosX
        val rightSpaceFromAnchor = rightSpaceFromAnchorMid + (anchorWidth / 2)

        val leftSpaceFromAnchorMid = anchorCenterPosX
        val leftSpaceFromAnchor = leftSpaceFromAnchorMid + (anchorWidth / 2)

        val aboveSpaceFromAnchorMid = centerPositionYWithoutStatusBar
        val aboveSpaceFromAnchor = aboveSpaceFromAnchorMid - (anchorHeight / 2)

        val belowSpaceFromAnchor = viewHeight - anchorBounds.bottom
        val belowSpaceFromAnchorMid = viewHeight - centerPositionYWithStatusBar

        val popupWidth = popupContentSize.width
        val popupHeight = popupContentSize.height

        val halfPopupContentSizeX = popupContentSize.width / 2
        val halfPopupContentSizeY = popupContentSize.height / 2

        val isCentralPositionTooltipX =
            halfPopupContentSizeX <= leftSpaceFromAnchorMid && halfPopupContentSizeX <= rightSpaceFromAnchorMid

        val isCentralPositionTooltipY =
            halfPopupContentSizeY <= aboveSpaceFromAnchorMid && halfPopupContentSizeY <= belowSpaceFromAnchorMid


        // Top alignment
        val topAlignmentOffsetX =
            anchorBounds.left + (anchorBounds.width - popupContentSize.width) / 2
        val topAlignmentOffsetY = anchorBounds.top - popupContentSize.height - arrowHeight
        val topAlignmentOffset = IntOffset(topAlignmentOffsetX, topAlignmentOffsetY.toInt())

        // Bottom Alignment
        val bottomAlignmentOffsetX =
            anchorBounds.left + (anchorBounds.width - popupContentSize.width) / 2
        val bottomAlignmentOffsetY = anchorBounds.bottom + arrowHeight
        val bottomAlignmentOffset =
            IntOffset(bottomAlignmentOffsetX, bottomAlignmentOffsetY.toInt())

        // Start alignment
        val startAlignmentOffsetX = anchorBounds.left - popupContentSize.width - arrowHeight - 5f
        val startAlignmentOffsetY = centerPositionYWithStatusBar - (popupContentSize.height / 2)
        val startAlignmentOffset =
            IntOffset(startAlignmentOffsetX.toInt(), startAlignmentOffsetY.toInt())

        // End Alignment
        val endAlignmentOffsetX = anchorBounds.right + arrowHeight + 5f
        val endAlignmentOffsetY = centerPositionYWithStatusBar - (popupContentSize.height / 2)
        val endAlignmentOffset = IntOffset(endAlignmentOffsetX.toInt(), endAlignmentOffsetY.toInt())

        // Center Alignment(Screen)
        val centerAlignmentX = windowSize.center.x - halfPopupContentSizeX
        val centerAlignmentY = windowSize.center.y - halfPopupContentSizeY
        val centerAlignmentOffset = IntOffset(centerAlignmentX, centerAlignmentY)

        val canPlaceAtTop = aboveSpaceFromAnchor >= popupContentSize.height
        val canPlaceAtBottom = belowSpaceFromAnchor >= popupContentSize.height
        val canPlaceAtStart = leftSpaceFromAnchorMid >= popupContentSize.width
        val canPlaceAtEnd = rightSpaceFromAnchorMid >= popupContentSize.width

        val alignmentOptions = mapOf(
            TooltipAlignment.TOP to listOf(
                TooltipAlignment.TOP to topAlignmentOffset,
                TooltipAlignment.BOTTOM to bottomAlignmentOffset,
                TooltipAlignment.START to startAlignmentOffset,
                TooltipAlignment.END to endAlignmentOffset
            ),
            TooltipAlignment.BOTTOM to listOf(
                TooltipAlignment.BOTTOM to bottomAlignmentOffset,
                TooltipAlignment.TOP to topAlignmentOffset,
                TooltipAlignment.START to startAlignmentOffset,
                TooltipAlignment.END to endAlignmentOffset
            ),
            TooltipAlignment.START to listOf(
                TooltipAlignment.START to startAlignmentOffset,
                TooltipAlignment.END to endAlignmentOffset,
                TooltipAlignment.TOP to topAlignmentOffset,
                TooltipAlignment.BOTTOM to bottomAlignmentOffset
            ),
            TooltipAlignment.END to listOf(
                TooltipAlignment.END to endAlignmentOffset,
                TooltipAlignment.START to startAlignmentOffset,
                TooltipAlignment.TOP to topAlignmentOffset,
                TooltipAlignment.BOTTOM to bottomAlignmentOffset
            )
        )

        val optionsForAlignment = alignmentOptions[alignment] ?: emptyList()

        for ((possibleAlignment, offset) in optionsForAlignment) {
            if (when (possibleAlignment) {
                    TooltipAlignment.TOP -> canPlaceAtTop
                    TooltipAlignment.BOTTOM -> canPlaceAtBottom
                    TooltipAlignment.START -> canPlaceAtStart
                    TooltipAlignment.END -> canPlaceAtEnd
                }
            ) {
                newAlignment = possibleAlignment
                popupPositionOffset = offset
                break
            } else {
                popupPositionOffset = centerAlignmentOffset
            }
        }

        var arrowPosition = 0f
        if (newAlignment == TooltipAlignment.TOP || newAlignment == TooltipAlignment.BOTTOM) {
            when {
                isCentralPositionTooltipX -> {
                    arrowPosition = halfPopupContentSizeX.toFloat()
                }

                popupWidth >= windowSize.width || halfPopupContentSizeX > leftSpaceFromAnchorMid -> {
                    arrowPosition = anchorCenterPosX
                }

                halfPopupContentSizeX > rightSpaceFromAnchorMid -> {
                    arrowPosition =
                        halfPopupContentSizeX + (halfPopupContentSizeX - rightSpaceFromAnchorMid)
                }

                halfPopupContentSizeX > leftSpaceFromAnchorMid -> {
                    arrowPosition = anchorCenterPosX
                }
            }
            onTooltipPosition.invoke(
                TooltipPositionData(
                    alignment = newAlignment,
                    arrowPositionOffset = Offset(arrowPosition, 0f)
                )
            )
        } else {
            when {
                isCentralPositionTooltipY -> {
                    arrowPosition = halfPopupContentSizeY.toFloat()
                }

                popupHeight >= windowSize.height -> {
                    arrowPosition = centerPositionYWithStatusBar

                }

                halfPopupContentSizeY > belowSpaceFromAnchorMid -> {
                    arrowPosition =
                        halfPopupContentSizeY + (halfPopupContentSizeY - belowSpaceFromAnchorMid)
                }

                halfPopupContentSizeY > aboveSpaceFromAnchorMid -> {
                    arrowPosition = centerPositionYWithoutStatusBar
                }
            }
            onTooltipPosition.invoke(
                TooltipPositionData(
                    alignment = newAlignment,
                    arrowPositionOffset = Offset(0f, arrowPosition)
                )
            )
        }
        return popupPositionOffset
    }
}

