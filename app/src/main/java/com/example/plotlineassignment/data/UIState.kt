package com.example.plotlineassignment.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.plotlineassignment.ui.components.TooltipAlignment

data class UIState(
    val targetElement: String,
    val alignment: TooltipAlignment,
    val textSize: TextUnit,
    val showImage: Boolean,
    val padding: Dp,
    val tooltipText: String,
    val textColor: Color,
    val backgroundColor: Color,
    val cornerRadius: Dp,
    val tooltipWidth: Dp,
    val arrowHeight: Dp,
    val arrowWidth: Dp
)
