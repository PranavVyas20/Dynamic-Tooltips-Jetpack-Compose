package com.example.plotlineassignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

data class UIState(
    val targetElement: String,
    val textSize: TextUnit,
    val padding: Dp,
    val tooltipText: String,
    val textColor: Color,
    val backgroundColor: Color,
    val cornerRadius: Dp,
    val tooltipWidth: Dp,
    val arrowHeight: Dp,
    val arrowWidth: Dp
)
