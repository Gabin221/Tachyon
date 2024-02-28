package com.example.tachyon.ui.statistiques

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class GraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val axisPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 4f
    }

    private val functionPaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 6f
    }

    private val pointPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 12f
        strokeCap = Paint.Cap.ROUND
    }

    private val margin = 100f
    private val axisMargin = 20f

    private val function: (Float) -> Float = { x ->
        // Ici, vous pouvez définir votre fonction
        // Par exemple, une fonction simple : f(x) = x^2
        x * x
    }

    private val points = listOf(
        Pair(1f, 1f),
        Pair(2f, 4f),
        Pair(3f, 9f),
        Pair(4f, 16f),
        Pair(5f, 25f)
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.apply {
            drawAxis()
            drawFunction()
            drawPoints()
        }
    }

    private fun Canvas.drawAxis() {
        drawLine(
            margin,
            height - margin,
            margin,
            axisMargin,
            axisPaint
        )
        drawLine(
            margin,
            height - margin,
            width - axisMargin,
            height - margin,
            axisPaint
        )
    }

    private fun Canvas.drawFunction() {
        val path = Path()
        val step = 10
        for (i in 0 until width - margin.toInt() step step) {
            val x = (i / step).toFloat()
            val y = height - margin - function(x / 100) * 100
            if (x == 0f) {
                path.moveTo(x + margin, y)
            } else {
                path.lineTo(x + margin, y)
            }
        }

        drawPath(path, functionPaint)
    }

    private fun Canvas.drawPoints() {
        for (point in points) {
            val x = point.first
            val y = point.second
            drawPoint(
                x * 100 + margin,
                height - margin - y * 100,
                pointPaint
            )
        }
    }
}
