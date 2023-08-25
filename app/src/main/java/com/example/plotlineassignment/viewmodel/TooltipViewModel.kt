package com.example.plotlineassignment.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.plotlineassignment.ui.screens.TooltipConfigScreenEvent
import com.example.plotlineassignment.model.UIState
import com.example.plotlineassignment.ui.components.TooltipAlignment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TooltipViewModel : ViewModel() {
    val targetElements = listOf("Button 1" to "btn-1", "Button 2" to "btn-2", "Button3" to "btn-3", "Button 4" to "btn-4", "Button 5" to "btn-5")
    var textSizeRange = 1..20
    val paddingSizeRange = 1..20
    val cornerRadiusRage = 1..10
    val arrowSizeRange = 8..18
    val availableAlignments = listOf("Top", "Bottom", "Start", "End")
    val availableColors =
        listOf(
            "White" to Color.White,
            "Black" to Color.Black,
            "Cyan" to Color.Cyan,
            "Red" to Color.Red,
            "Green" to Color.Green,
            "Blue" to Color.Blue,
            "Yellow" to Color.Yellow
        )
    private var _uiState: MutableStateFlow<UIState> = MutableStateFlow(
        UIState(
            alignment = TooltipAlignment.TOP,
            targetElement = "btn-1",
            textSize = 14.sp,
            padding = 8.dp,
            tooltipText = "Tooltip text!",
            textColor = Color.White,
            backgroundColor = Color.Black,
            cornerRadius = 8.dp,
            tooltipWidth = 10.dp,
            arrowHeight = 10.dp,
            arrowWidth = 10.dp
        )
    )
    val uiState: StateFlow<UIState> = _uiState

    fun resetUIState() {
        _uiState.value = UIState(
            targetElement = "btn-1",
            alignment = TooltipAlignment.TOP,
            textSize = 14.sp,
            padding = 8.dp,
            tooltipText = "Tooltip text!",
            textColor = Color.White,
            backgroundColor = Color.Black,
            cornerRadius = 8.dp,
            tooltipWidth = 10.dp,
            arrowHeight = 10.dp,
            arrowWidth = 10.dp
        )
    }

    fun onEvent(event: TooltipConfigScreenEvent) {
        when (event) {
            is TooltipConfigScreenEvent.onTargetElementChanged -> {
                _uiState.value = _uiState.value.copy(
                    targetElement = event.targetElement
                )
            }

            is TooltipConfigScreenEvent.onTextSizeChanged -> {
                _uiState.value = _uiState.value.copy(
                    textSize = event.textSize.toFloat().sp
                )
            }

            is TooltipConfigScreenEvent.onPaddingChanged -> {
                _uiState.value = _uiState.value.copy(
                    padding = event.padding.toFloat().dp
                )
            }

            is TooltipConfigScreenEvent.onTooltipTextChanged -> {
                _uiState.value = _uiState.value.copy(
                    tooltipText = event.tooltipText
                )
            }

            is TooltipConfigScreenEvent.onTextColorChanged -> {
                _uiState.value = _uiState.value.copy(
                    textColor = event.textColor
                )
            }

            is TooltipConfigScreenEvent.onBackgroundColorChanged -> {
                _uiState.value = _uiState.value.copy(
                    backgroundColor = event.backgroundColor
                )
            }

            is TooltipConfigScreenEvent.onCornerRadiusChanged -> {
                _uiState.value = _uiState.value.copy(
                    cornerRadius = event.cornerRadius.toFloat().dp
                )
            }

            is TooltipConfigScreenEvent.onTooltipWidthChanged -> {
                _uiState.value = _uiState.value.copy(
                    tooltipWidth = event.tooltipWidth.toFloat().dp
                )
            }

            is TooltipConfigScreenEvent.onArrowHeightChanged -> {
                _uiState.value = _uiState.value.copy(
                    arrowHeight = event.arrowHeight.toFloat().dp
                )
            }

            is TooltipConfigScreenEvent.onArrowWidthChanged -> {
                _uiState.value = _uiState.value.copy(
                    arrowWidth = event.arrowWidth.toFloat().dp
                )
            }

            is TooltipConfigScreenEvent.onTooltipAlignmentChanged -> {
                _uiState.value = _uiState.value.copy(
                    alignment = event.alignment
                )
            }
        }
    }
}