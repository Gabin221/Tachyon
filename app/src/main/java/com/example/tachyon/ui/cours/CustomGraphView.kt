package com.example.tachyon.ui.cours

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

    private val xValues = mutableListOf<Float>()
    private val yValues = mutableListOf<Float>()

    private val functionValues = mutableListOf<Float>()
    private val scatterPoints = mutableListOf<Pair<Float, Float>>()

    val padding = 50 // Valeur de la marge ou de l'espace autour du contenu du graphique

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
        return x * width / 15 // maxXValue est la valeur maximale de votre axe X
    }

    private fun calculatePointY(y: Float, height: Float): Float {
        // Calculer la position Y du point en fonction de la hauteur de la vue
        // Vous pouvez ajuster cette fonction en fonction de vos besoins spécifiques
        return height - y * height / 20 // maxYValue est la valeur maximale de votre axe Y
    }

    fun setFunctionValues(xValues: List<Float>, yValues: List<Float>) {
        this.xValues.clear()
        this.xValues.addAll(xValues)
        this.yValues.clear()
        this.yValues.addAll(yValues)
        invalidate()
    }

    fun setScatterPoints(points: List<Pair<Float, Float>>) {
        scatterPoints.clear()
        scatterPoints.addAll(points)
        invalidate()
    }

    private val axisPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 3f
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 30f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dessiner l'axe X
        canvas.drawLine(
            padding.toFloat(),
            height - padding.toFloat(),
            width.toFloat() - padding.toFloat(),
            height - padding.toFloat(),
            axisPaint
        )

        // Dessiner l'axe Y
        canvas.drawLine(
            padding.toFloat(),
            height - padding.toFloat(),
            padding.toFloat(),
            0f,
            axisPaint
        )

        // Dessiner les graduations sur l'axe X
        val intervalX = (width - 2 * padding) / 10
        for (i in 0..10) {
            val x = padding + i * intervalX
            canvas.drawLine(x.toFloat(), height - padding.toFloat(), x.toFloat(), height - padding.toFloat() - 20, axisPaint)
            canvas.drawText(i.toString(), x.toFloat() - 10, height - padding.toFloat() + 40, textPaint)
        }

        // Dessiner les graduations sur l'axe Y
        val intervalY = (height - 2 * padding) / 10
        for (i in 0..10) {
            val y = height - padding - i * intervalY
            canvas.drawLine(padding.toFloat(), y.toFloat(), padding.toFloat() + 20, y.toFloat(), axisPaint)
            canvas.drawText(i.toString(), padding.toFloat() - 50, y.toFloat() + 10, textPaint)
        }

        // Dessiner la fonction et le nuage de points
        drawFunction(canvas)
        drawScatter(canvas)
    }


    private fun drawFunction(canvas: Canvas) {
        if (xValues.size < 2 || yValues.size < 2 || xValues.size != yValues.size) return
        val graphWidth = width - 2 * padding // Largeur disponible pour le graphique après avoir pris en compte les marges
        val height = height.toFloat()
        val dx = graphWidth / (xValues.size - 1)
        var prevX = padding.toFloat() // Commencez à la marge gauche du graphique
        var prevY = calculatePointY(yValues[0], height)
        for (i in 1 until xValues.size) {
            val x = padding + i * dx // Ajuster les valeurs de x pour qu'elles commencent à la marge gauche du graphique
            val y = calculatePointY(yValues[i], height)
            canvas.drawLine(prevX, prevY, x.toFloat(), y, functionPaint)
            prevX = x.toFloat()
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
