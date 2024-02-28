package com.example.tachyon.ui.statistiques

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class CustomGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val functionPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
    }

    private val scatterPaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 10f
    }

    private val functionValues = mutableListOf<Float>()
    private val scatterPoints = mutableListOf<Pair<Float, Float>>()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Mettre à jour la taille et recalculer les positions si nécessaire
        // Par exemple, recalculez les coordonnées des points de votre graphique
        // en fonction des nouvelles dimensions
        invalidate() // Pour forcer le redessin lorsque la taille change
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Calculer la taille souhaitée de la vue en fonction des données à afficher
        // Par exemple, la taille souhaitée peut dépendre du nombre de points dans votre graphique
        val desiredWidth = 500 // Remplacez 500 par la largeur souhaitée
        val desiredHeight = 500 // Remplacez 500 par la hauteur souhaitée

        // Mettre à jour les dimensions de la vue en fonction de la taille souhaitée
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(desiredWidth, widthSize)
            else -> desiredWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }

    private fun calculatePointX(x: Float, width: Float): Float {
        // Calculer la position X du point en fonction de la largeur de la vue
        // Vous pouvez ajuster cette fonction en fonction de vos besoins spécifiques
        return x * width / 10 // maxXValue est la valeur maximale de votre axe X
    }

    private fun calculatePointY(y: Float, height: Float): Float {
        // Calculer la position Y du point en fonction de la hauteur de la vue
        // Vous pouvez ajuster cette fonction en fonction de vos besoins spécifiques
        return height - y * height / 10 // maxYValue est la valeur maximale de votre axe Y
    }

    fun setFunctionValues(values: List<Float>) {
        functionValues.clear()
        functionValues.addAll(values)
        invalidate()
    }

    fun setScatterPoints(points: List<Pair<Float, Float>>) {
        scatterPoints.clear()
        scatterPoints.addAll(points)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.let {
            drawFunction(it)
            drawScatter(it)
        }
    }

    private fun drawFunction(canvas: Canvas) {
        if (functionValues.size < 2) return
        val width = width.toFloat()
        val height = height.toFloat()
        val dx = width / (functionValues.size - 1)
        var prevX = 0f
        var prevY = calculatePointY(functionValues[0], height)
        for (i in 1 until functionValues.size) {
            val x = i * dx
            val y = calculatePointY(functionValues[i], height)
            canvas.drawLine(prevX, prevY, x, y, functionPaint)
            prevX = x
            prevY = y
        }
    }

    private fun drawScatter(canvas: Canvas) {
        for (point in scatterPoints) {
            val x = calculatePointX(point.first, width.toFloat())
            val y = calculatePointY(point.second, height.toFloat())
            canvas.drawPoint(x, y, scatterPaint)
        }
    }
}
