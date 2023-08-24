package com.example.plotlineassignment.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.plotlineassignment.TooltipConfigScreenEvent
import com.example.plotlineassignment.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TooltipViewModel : ViewModel() {
    val targetElements = listOf("Button 1", "Button 2", "Button3", "Button 4", "Button 5")
    var textSizeRange = 1..20
    val paddingSizeRange = 1..20
    val cornerRadiusRage = 1..10
    val arrowSizeRange = 8..18
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
            targetElement = "Button 1",
            textSize = 18.sp,
            padding = 0.dp,
            tooltipText = "Tooltip text!",
            textColor = Color.White,
            backgroundColor = Color.Black,
            cornerRadius = 8.dp,
            tooltipWidth = 0.dp,
            arrowHeight = 10.dp,
            arrowWidth = 10.dp
        )
    )
    val uiState: StateFlow<UIState> = _uiState

    fun resetUIState() {
        _uiState.value = UIState(
            targetElement = "Button 1",
            textSize = 18.sp,
            padding = 0.dp,
            tooltipText = "Tooltip text!",
            textColor = Color.White,
            backgroundColor = Color.Black,
            cornerRadius = 8.dp,
            tooltipWidth = 0.dp,
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
        }
    }
}