package com.example.plotlineassignment.ui.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.plotlineassignment.TooltipConfigScreenEvent
import com.example.plotlineassignment.ui.components.TooltipAlignment
import com.example.plotlineassignment.ui.components.TooltipPopup
import com.example.plotlineassignment.ui.components.rememberTooltipState
import com.example.plotlineassignment.ui.viewmodels.TooltipViewModel

@Composable
fun TooltipRenderScreen(viewModel: TooltipViewModel, navController: NavController) {
    val uiState  = viewModel.uiState.collectAsStateWithLifecycle().value
    BackHandler {
        viewModel.resetUIState()
        navController.popBackStack()
    }
    LaunchedEffect(key1 = Unit) {
        Log.d("ui-state", uiState.toString())
    }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (btn1, btn2, btn3, btn4, btn5, cmpnt) = createRefs()
        TooltipPopup(
            modifier = Modifier.constrainAs(btn1) {
                start.linkTo(parent.start, 8.dp)
                top.linkTo(parent.top)
            },
            tooltipState = rememberTooltipState(initiallyTooltipVisible = false),
            anchor = { tooltipState ->
                Button(
                    onClick = { tooltipState.toggleVisibility() }) {
                    Text(text = "Button 1")
                }
            },
            alignment = TooltipAlignment.START,
        )
        TooltipPopup(
            modifier = Modifier.constrainAs(btn2) {
                end.linkTo(parent.end, 8.dp)
                top.linkTo(parent.top, 8.dp)
            },
            tooltipState = rememberTooltipState(initiallyTooltipVisible = false),
            anchor = { tooltipState ->
                Button(onClick = { tooltipState.toggleVisibility() }) {
                    Text(text = "Button 2")
                }
            },
            alignment = TooltipAlignment.END,
        )
        TooltipPopup(
            tooltipState = rememberTooltipState(initiallyTooltipVisible = false),
            modifier = Modifier.constrainAs(btn3) {
                centerVerticallyTo(parent)
                centerHorizontallyTo(parent)
            },
            anchor = { tooltipState ->
                Button(onClick = { tooltipState.toggleVisibility() }) {
                    Text(text = "Button 3")
                }
            },
            alignment = TooltipAlignment.BOTTOM,
        )
        TooltipPopup(
            modifier = Modifier.constrainAs(btn4) {
                bottom.linkTo(parent.bottom, 8.dp)
                start.linkTo(parent.start, 8.dp)
            },
            tooltipState = rememberTooltipState(initiallyTooltipVisible = false),
            anchor = { tooltipState ->
                Button(onClick = { tooltipState.toggleVisibility() }) {
                    Text(text = "Button 4")
                }
            },
            alignment = TooltipAlignment.TOP,
        )
        TooltipPopup(
            modifier = Modifier.constrainAs(btn5) {
                bottom.linkTo(parent.bottom, 8.dp)
                end.linkTo(parent.end, 8.dp)
            },
            tooltipState = rememberTooltipState(initiallyTooltipVisible = false),
            anchor = { tooltipState ->
                Button(onClick = { tooltipState.toggleVisibility() }) {
                    Text(text = "Button 5")
                }
            },
            alignment = TooltipAlignment.TOP,
        )
    }
}