package com.example.plotlineassignment.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.plotlineassignment.ui.components.TooltipAlignment

data class TooltipPopupProperties(
    val alignment: TooltipAlignment,
    val textSize: TextUnit,
    val showImage: Boolean,
    val padding: Dp,
    val tooltipText: String,
    val textColor: Color,
    val backgroundColor: Color,
    val cornerRadius: Dp,
    val tooltipWidth: Dp = 0.dp,
    val arrowHeight: Dp,
    val arrowWidth: Dp = 0.dp
)
