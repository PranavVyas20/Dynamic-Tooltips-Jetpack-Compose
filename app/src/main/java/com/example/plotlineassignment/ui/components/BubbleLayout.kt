package com.example.plotlineassignment.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.plotlineassignment.R

@Composable
fun BubbleLayout(
    modifier: Modifier = Modifier,
    alignment: TooltipAlignment,
    showImage: Boolean,
    tooltipText: String,
    textSize: TextUnit,
    tooltipTextColor: Color,
    arrowPositionOffset: Offset,
    tooltipPadding: Dp,
    tooltipCornerRadius: Dp,
    tooltipBackgroundColor: Color,
    arrowHeight: Dp,
) {
    Box(
        modifier = modifier.drawBehind {
            val path = Path()
            when (alignment) {
                TooltipAlignment.TOP -> {
                    Log.d("tooltip-alignment", "top")
                    // This is basically the height of the box in which we are drawing
                    val localY = drawContext.size.height
                    val position = Offset(arrowPositionOffset.x, localY)
                    path.apply {
                        moveTo(x = position.x, y = localY)
                        lineTo(x = position.x - arrowHeight.value, y = position.y)
                        lineTo(x = position.x, y = position.y + arrowHeight.value)
                        lineTo(x = position.x + arrowHeight.value, y = position.y)
                    }
                }

                TooltipAlignment.BOTTOM -> {
                    Log.d("tooltip-alignment", "bottom")
                    val position = Offset(arrowPositionOffset.x, 0f)
                    path.apply {
                        moveTo(x = position.x, y = position.y)
                        lineTo(x = position.x + arrowHeight.value, y = position.y)
                        lineTo(x = position.x, y = position.y - arrowHeight.value)
                        lineTo(x = position.x - arrowHeight.value, y = position.y)
                    }
                }

                TooltipAlignment.START -> {
                    Log.d("tooltip-alignment", "start")
                    val localX = drawContext.size.width
                    val position = Offset(localX, arrowPositionOffset.y)
                    path.apply {
                        moveTo(position.x, position.y)
                        lineTo(position.x, position.y - arrowHeight.value)
                        lineTo(position.x + arrowHeight.value, position.y)
                        lineTo(position.x, position.y + arrowHeight.value)
                    }
                }

                TooltipAlignment.END -> {
                    Log.d("tooltip-alignment", "end")
                    val position =
                        Offset(0f, arrowPositionOffset.y)
                    path.apply {
                        moveTo(position.x, position.y)
                        lineTo(position.x, position.y - arrowHeight.value)
                        lineTo(position.x - arrowHeight.value, position.y)
                        lineTo(position.x, position.y + arrowHeight.value)
                    }
                }
            }
            drawPath(path = path, color = Color.Black)
            path.close()
        }

    ) {
        Box(
            modifier = Modifier.background(
                shape = RoundedCornerShape(tooltipCornerRadius),
                color = tooltipBackgroundColor
            )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(tooltipPadding)
            ) {
                Text(
                    text = tooltipText,
                    fontSize = textSize,
                    color = tooltipTextColor
                )
                if (showImage) {
                    Image(
                        painter = painterResource(id = R.drawable.sample),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}