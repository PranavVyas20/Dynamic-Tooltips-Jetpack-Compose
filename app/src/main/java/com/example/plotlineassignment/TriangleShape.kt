package com.example.plotlineassignment

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(x = size.width, y = size.height)
            lineTo(x = 0f, y = size.height)
        }
        return Outline.Generic(trianglePath)
    }
}