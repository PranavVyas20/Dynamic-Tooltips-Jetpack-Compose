package com.example.plotlineassignment.ui.screens

import androidx.compose.ui.graphics.Color
import com.example.plotlineassignment.ui.components.TooltipAlignment

sealed class TooltipConfigScreenEvent{
    data class onTargetElementChanged(val targetElement: String): TooltipConfigScreenEvent()
    data class onTooltipAlignmentChanged(val alignment: TooltipAlignment): TooltipConfigScreenEvent()
    data class onTextSizeChanged(val textSize: String): TooltipConfigScreenEvent()
    data class onPaddingChanged(val padding: String): TooltipConfigScreenEvent()
    data class onTooltipTextChanged(val tooltipText: String): TooltipConfigScreenEvent()
    data class onTextColorChanged(val textColor: Color): TooltipConfigScreenEvent()
    data class onBackgroundColorChanged(val backgroundColor: Color): TooltipConfigScreenEvent()
    data class onCornerRadiusChanged(val cornerRadius: String): TooltipConfigScreenEvent()
    data class onTooltipWidthChanged(val tooltipWidth: String): TooltipConfigScreenEvent()
    data class onArrowHeightChanged(val arrowHeight: String): TooltipConfigScreenEvent()
    data class onArrowWidthChanged(val arrowWidth: String): TooltipConfigScreenEvent()
}
