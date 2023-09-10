package com.example.plotlineassignment.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.plotlineassignment.data.TooltipPopupProperties
import com.example.plotlineassignment.ui.components.TooltipPopup
import com.example.plotlineassignment.ui.components.rememberTooltipState
import com.example.plotlineassignment.viewmodel.TooltipViewModel

@Composable
fun TooltipRenderScreen(viewModel: TooltipViewModel, navController: NavController) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    BackHandler {
        viewModel.resetUIState()
        navController.popBackStack()
    }
    val properties = TooltipPopupProperties(
        alignment = uiState.alignment,
        textSize = uiState.textSize,
        backgroundColor = uiState.backgroundColor,
        textColor = uiState.textColor,
        padding = uiState.padding,
        showImage = uiState.showImage,
        cornerRadius = uiState.cornerRadius,
        tooltipWidth = uiState.tooltipWidth,
        tooltipText = uiState.tooltipText,
        arrowWidth = uiState.arrowWidth,
        arrowHeight = uiState.arrowHeight
    )

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)) {
        val (btn1, btn2, btn3, btn4, btn5) = createRefs()
        TooltipItem(
            properties = properties,
            modifier = Modifier.constrainAs(btn1) {
                start.linkTo(parent.start, 8.dp)
                top.linkTo(parent.top, 8.dp)
            },
            id = "btn-1",
            text = "Button 1",
            targetElementId = uiState.targetElement
        )

        TooltipItem(
            properties = properties,
            modifier = Modifier.constrainAs(btn2) {
                end.linkTo(parent.end, 8.dp)
                top.linkTo(parent.top, 8.dp)
            },
            id = "btn-2",
            text = "Button 2",
            targetElementId = uiState.targetElement
        )
        TooltipItem(
            properties = properties,
            modifier = Modifier.constrainAs(btn3) {
                centerVerticallyTo(parent)
                centerHorizontallyTo(parent)
            },
            id = "btn-3",
            text = "Button 3",
            targetElementId = uiState.targetElement
        )
        TooltipItem(
            properties = properties,
            modifier = Modifier.constrainAs(btn4) {
                bottom.linkTo(parent.bottom, 8.dp)
                start.linkTo(parent.start, 8.dp)
            },
            id = "btn-4",
            text = "Button 4",
            targetElementId = uiState.targetElement
        )
        TooltipItem(
            properties = properties,
            modifier = Modifier.constrainAs(btn5) {
                bottom.linkTo(parent.bottom, 8.dp)
                end.linkTo(parent.end, 8.dp)
            },
            id = "btn-5",
            text = "Button 5",
            targetElementId = uiState.targetElement
        )
    }
}

@Composable
fun TooltipItem(
    properties: TooltipPopupProperties,
    modifier: Modifier,
    targetElementId: String,
    id: String,
    text: String
) {
    if (targetElementId == id) {
        TooltipPopup(
            modifier = modifier,
            tooltipProperties = properties,
            anchor = { tooltipState ->
                Button(shape = RoundedCornerShape(size = 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = modifier,
                    onClick = { tooltipState.toggleVisibility() }) {
                    Text(text = text, color = Color.Black)
                }
            },
            tooltipState = rememberTooltipState(
                initiallyTooltipVisible = false
            )
        )
    } else {
        Button(shape = RoundedCornerShape(size = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = modifier,
            onClick = { }) {
            Text(text = text, color = Color.Black)
        }
    }
}