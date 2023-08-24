@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.plotlineassignment.ui.screens

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.plotlineassignment.ui.theme.GrayBackgroundColor


val lightGrayColor = Color(0xFFd9d9d9)

@Composable
@Preview
fun TooltipConfigScreen() {
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
            TargetElementField()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                TooltipStylingField(heading = "Text Size", modifier = Modifier.weight(1f))
                TooltipStylingField(heading = "Padding", modifier = Modifier.weight(1f))
            }
            TooltipStylingField(heading = "Tooltip text")
            TooltipStylingField(heading = "Text Color")
            TooltipStylingField(heading = "Background Color")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                TooltipStylingField(heading = "Corner Radius", modifier = Modifier.weight(1f))
                TooltipStylingField(heading = "Tooltip width", modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                TooltipStylingField(heading = "Arrow height", modifier = Modifier.weight(1f))
                TooltipStylingField(heading = "Arrow width", modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { /*TODO*/ },
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
fun TooltipStylingField(heading: String, modifier: Modifier = Modifier.fillMaxWidth()) {
    Column(
        modifier = modifier
    ) {
        var value by remember { mutableStateOf("") }
        Text(text = heading)
        Spacer(modifier = Modifier.height(7.dp))
        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                value = it.filter { it.isLetter() }
            }
        )
    }
}

@Composable
@Preview
fun TargetElementField() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Target Element")
        Spacer(modifier = Modifier.height(7.dp))
        TargetElementDropdown()
    }
}

@Composable
fun TargetElementDropdown() {
    val elementsList = listOf("Button 1", "Button 2", "Button3", "Button 4", "Button 5")
    var isExpanded by remember { mutableStateOf(false) }
    var currentSelectedItem by remember {
        mutableStateOf(elementsList[0])
    }
    Box(
        modifier = Modifier
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
            Text(
                text = currentSelectedItem,
                color = lightGrayColor,
                modifier = Modifier.constrainAs(elementNameText) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                })
            Icon(
                tint = lightGrayColor,
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "down-arrow",
                modifier = Modifier
                    .clickable { isExpanded = true }
                    .constrainAs(dropDownIcon) {
                        centerVerticallyTo(parent)
                        end.linkTo(parent.end)
                    })

            DropdownMenu(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                elementsList.forEachIndexed { index, element ->
                    DropdownMenuItem(
                        text = { Text(text = element) },
                        onClick = {
                            isExpanded = false
                            currentSelectedItem = element
                        }
                    )
                }
            }
        }
    }
}
