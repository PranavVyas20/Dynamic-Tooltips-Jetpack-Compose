package com.example.plotlineassignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.plotlineassignment.ui.theme.DefaultDark

@Composable
fun Tooltip(
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    backgroundColor: Color = DefaultDark,
    cornerRadius: Dp = 4.dp,
    arrowHeight: Dp = 10.dp,
    arrowWidth: Dp = 10.dp,
    arrowAlignment: TooltipAlignment
) {
    ConstraintLayout(modifier = modifier) {
        val (tooltipContent, arrow) = createRefs()
        var rotation by remember {
            mutableStateOf(0f)
        }
        Box(
            modifier = Modifier
                .constrainAs(tooltipContent) {
                    when (arrowAlignment) {
                        TooltipAlignment.TOP -> {
                            bottom.linkTo(parent.bottom)
                        }

                        TooltipAlignment.START -> {
                            end.linkTo(parent.end)
                        }

                        else -> top.linkTo(parent.top)
                    }
                }
                .background(color = backgroundColor, shape = RoundedCornerShape(cornerRadius))
        ) {
            Text(
                text = "Tooltip text here",
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                color = textColor,
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(arrow) {
                    when (arrowAlignment) {
                        TooltipAlignment.TOP -> {
                            centerHorizontallyTo(tooltipContent)
                            bottom.linkTo(tooltipContent.top)
                        }

                        TooltipAlignment.BOTTOM -> {
                            centerHorizontallyTo(tooltipContent)
                            top.linkTo(tooltipContent.bottom)
                            rotation = -180f
                        }

                        TooltipAlignment.START -> {
                            rotation = -90f
                            centerVerticallyTo(tooltipContent)
                            end.linkTo(tooltipContent.start)
                        }

                        TooltipAlignment.END -> {
                            rotation = 90f
                            centerVerticallyTo(tooltipContent)
                            start.linkTo(tooltipContent.end)
                        }
                    }
                }
                .rotate(rotation)
                .size(10.dp)
                .clip(shape = TriangleShape())
                .background(color = DefaultDark)
        )

    }
}