@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.plotlineassignment.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.plotlineassignment.Destinations
import com.example.plotlineassignment.TooltipConfigScreenEvent
import com.example.plotlineassignment.ui.viewmodels.TooltipViewModel
import com.example.plotlineassignment.ui.theme.GrayBackgroundColor


val lightGrayColor = Color(0xFFd9d9d9)

@Composable
fun TooltipConfigScreen(viewModel: TooltipViewModel, navController: NavController) {
//    val ctx = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayBackgroundColor)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(20.dp)
        ) {
            DropdownSelectionField(initialSelectedItem = uiState.targetElement,
                title = "Target Element",
                id = "target-element",
                modifier = Modifier.fillMaxWidth(),
                selectionsList = viewModel.targetElements,
                onItemSelect = { targetElement ->
                    viewModel.onEvent(TooltipConfigScreenEvent.onTargetElementChanged(targetElement as String))
                })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                DropdownSelectionField(initialSelectedItem = uiState.textSize.toString(),
                    title = "Text Size",
                    id = "text-size",
                    modifier = Modifier.weight(1f),
                    selectionsList = viewModel.textSizeRange.map { it.toString() },
                    onItemSelect = { selectedSize ->
                        viewModel.onEvent(TooltipConfigScreenEvent.onTextSizeChanged(selectedSize as String))
                    })
                DropdownSelectionField(initialSelectedItem = uiState.padding.toString(),
                    title = "Padding",
                    id = "padding",
                    modifier = Modifier.weight(1f),
                    selectionsList = viewModel.paddingSizeRange.map { it.toString() },
                    onItemSelect = { paddingItem ->
                        viewModel.onEvent(TooltipConfigScreenEvent.onPaddingChanged(paddingItem as String))
                    })
            }
            TooltipStylingField(onTextValueChanged = { text ->
                viewModel.onEvent(TooltipConfigScreenEvent.onTooltipTextChanged(text))
            }, heading = "Tooltip text")

            DropdownSelectionField(initialSelectedItem = viewModel.availableColors[0].first,
                selectionsList = viewModel.availableColors.map { it.first },
                availableColors = viewModel.availableColors.map { it.second },
                title = "Text Color",
                id = "text-color",
                modifier = Modifier.fillMaxWidth(),
                onItemSelect = { selectedTextColor ->
                    viewModel.onEvent(TooltipConfigScreenEvent.onTextColorChanged(selectedTextColor as Color))
                })
            DropdownSelectionField(initialSelectedItem = viewModel.availableColors[0].first,
                selectionsList = viewModel.availableColors.map { it.first },
                availableColors = viewModel.availableColors.map { it.second },
                title = "Background Color",
                id = "background-color",
                modifier = Modifier.fillMaxWidth(),
                onItemSelect = { selectedBackgroundColor ->
                    viewModel.onEvent(
                        TooltipConfigScreenEvent.onBackgroundColorChanged(
                            selectedBackgroundColor as Color
                        )
                    )
                })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                DropdownSelectionField(initialSelectedItem = uiState.cornerRadius.toString(),
                    selectionsList = viewModel.cornerRadiusRage.map { it.toString() },
                    id = "corner-radius",
                    title = "Corner Radius",
                    modifier = Modifier.weight(1f),
                    onItemSelect = { selectedCornerRadius ->
                        viewModel.onEvent(
                            TooltipConfigScreenEvent.onCornerRadiusChanged(
                                selectedCornerRadius as String
                            )
                        )
                    })
                DropdownSelectionField(initialSelectedItem = uiState.tooltipWidth.toString(),
                    selectionsList = viewModel.cornerRadiusRage.map { it.toString() },
                    id = "tooltip-width",
                    title = "Tooltip width",
                    modifier = Modifier.weight(1f),
                    onItemSelect = {})
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                DropdownSelectionField(initialSelectedItem = uiState.arrowHeight.toString(),
                    selectionsList = viewModel.arrowSizeRange.map { it.toString() },
                    id = "arrow-height",
                    title = "Arrow height",
                    modifier = Modifier.weight(1f),
                    onItemSelect = { selectedArrowHeight ->
                        viewModel.onEvent(
                            TooltipConfigScreenEvent.onArrowHeightChanged(
                                selectedArrowHeight as String
                            )
                        )
                    })
                DropdownSelectionField(initialSelectedItem = uiState.arrowWidth.toString(),
                    selectionsList = viewModel.arrowSizeRange.map { it.toString() },
                    id = "arrow-width",
                    title = "Arrow Width",
                    modifier = Modifier.weight(1f),
                    onItemSelect = { selectedArrowWidth ->
                        viewModel.onEvent(
                            TooltipConfigScreenEvent.onArrowWidthChanged(
                                selectedArrowWidth as String
                            )
                        )
                    })
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { navController.navigate(Destinations.TooltipRenderScreen) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    "Render Tooltip",
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipStylingField(
    onTextValueChanged: (value: String) -> Unit,
    heading: String, modifier: Modifier = Modifier.fillMaxWidth()
) {
    Column(
        modifier = modifier
    ) {
        var value by remember { mutableStateOf("") }
        Text(text = heading)
        Spacer(modifier = Modifier.height(7.dp))
        TextField(
            shape = RoundedCornerShape(6.dp),
            placeholder = { Text(text = "Input", color = lightGrayColor) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = lightGrayColor,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .border(1.dp, color = lightGrayColor, shape = RoundedCornerShape(6.dp)),
            value = value,
            onValueChange = {
                val text = it.filter { it.isLetter() || it.isWhitespace() }
                onTextValueChanged(text)
                value = text
            })
    }
}

@Composable
fun DropdownSelectionField(
    availableColors: List<Color>? = null,
    initialSelectedItem: String,
    onItemSelect: (selectedItem: Any) -> Unit,
    selectionsList: List<String>,
    title: String,
    id: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(title)
        Spacer(modifier = Modifier.height(7.dp))
        DropdownSelection(availableColors, initialSelectedItem, onItemSelect, selectionsList)
    }
}

@Composable
fun DropdownSelection(
    availableColors: List<Color>? = null,
    initialSelectedItem: String,
    onItemSelect: (selectedItem: Any) -> Unit,
    selectionsList: List<String>
) {
    var isExpanded by remember { mutableStateOf(false) }
    var currentSelectedItem by remember {
        mutableStateOf(initialSelectedItem)
    }
    Box(
        modifier = Modifier
            .clickable {
                isExpanded = true
            }
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White)
            .border(1.dp, color = lightGrayColor, shape = RoundedCornerShape(6.dp))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            val (elementNameText, dropDownIcon, dropDownMenu) = createRefs()
            Text(text = currentSelectedItem,
                color = lightGrayColor,
                modifier = Modifier.constrainAs(elementNameText) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                })
            Icon(tint = lightGrayColor,
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "down-arrow",
                modifier = Modifier
                    .constrainAs(dropDownIcon) {
                        centerVerticallyTo(parent)
                        end.linkTo(parent.end)
                    })

            DropdownMenu(modifier = Modifier.padding(horizontal = 12.dp),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                selectionsList.forEachIndexed { index, element ->
                    DropdownMenuItem(text = { Text(text = element) }, onClick = {
                        isExpanded = false
                        availableColors?.let {
                            onItemSelect(it[index])
                        } ?: onItemSelect(element)
                        currentSelectedItem = element
                    })
                }
            }
        }
    }
}
