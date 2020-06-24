package com.kzsobolewski.sudoku.main.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.kzsobolewski.sudoku.main.fragments.ViewPaints

typealias Position = Pair<Int, Int>

class GameBoardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val viewPaints = ViewPaints(resources)

    private val boxSize = 3
    private val boardSize = 9

    private var cellSizePixels = 0f

    private var selectedRow = -1
    private var selectedColumn = -1
    private lateinit var listener: OnClickListener

    private var cells: Array<Array<Rect>> =
        Array(boardSize) { Array(boardSize) { Rect(0, 0, 0, 0) } }
    private var cellsData: Array<IntArray> = Array(boardSize) { IntArray(boardSize) { 0 } }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = measuredHeight.coerceAtMost(measuredWidth)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        cellSizePixels = (width / boardSize).toFloat()
        makeCells()
        canvas?.let {
            drawLines(canvas)
            fillCells(canvas)
        }
    }

    private fun makeCells() {
        (0 until boardSize).forEach { row ->
            (0 until boardSize).forEach { column ->
                cells[row][column] = Rect(
                    row * cellSizePixels.toInt(),
                    column * cellSizePixels.toInt(),
                    (row + 1) * cellSizePixels.toInt(),
                    (column + 1) * cellSizePixels.toInt()
                )
            }
        }
    }

    private fun fillCells(canvas: Canvas) {
        (0 until boardSize).forEach { row ->
            (0 until boardSize).forEach { column ->
                val boardValue = cellsData[row][column]
                val paintToUse =
                    if (row == selectedRow && column == selectedColumn)
                        viewPaints.selectedCirclePaint
                    else if (boardValue != 0)
                        viewPaints.fixedCirclePaint
                    else viewPaints.circlePaint
                drawCell(canvas, cells[row][column], paintToUse)
                if (boardValue != 0)
                    drawTextInsideRectangle(canvas, cells[row][column], boardValue)
            }
        }
    }


    private fun drawCell(canvas: Canvas, rect: Rect, paint: Paint) {
        canvas.drawCircle(
            rect.exactCenterX(),
            rect.exactCenterY(),
            cellSizePixels / 2 - 8,
            paint
        )
    }


    private fun drawTextInsideRectangle(canvas: Canvas, rect: Rect, digit: Int) {
        val str = digit.toString()
        val xOffset = viewPaints.textPaint.measureText(str) * 0.5f
        val yOffset = viewPaints.textPaint.fontMetrics.ascent * -0.4f
        val textX = rect.exactCenterX() - xOffset
        val textY = rect.exactCenterY() + yOffset
        canvas.drawText(str, textX, textY, viewPaints.textPaint)
    }


    private fun drawLines(canvas: Canvas) {
        (1 until boardSize).forEach {
            if (it % boxSize == 0) {
                canvas.drawLine(
                    it * cellSizePixels,
                    0f,
                    it * cellSizePixels,
                    height.toFloat(),
                    viewPaints.linePaint
                )
                canvas.drawLine(
                    0f,
                    it * cellSizePixels,
                    width.toFloat(),
                    it * cellSizePixels,
                    viewPaints.linePaint
                )
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> handleTouchEvent(event.x, event.y)
            else -> super.onTouchEvent(event)
        }
    }

    private fun handleTouchEvent(x: Float, y: Float): Boolean {
        val row = (x / cellSizePixels).toInt()
        val column = (y / cellSizePixels).toInt()
        listener.onClick(row to column)
        return true
    }

    fun updateCurrentPosition(position: Position) {
        selectedRow = position.first
        selectedColumn = position.second
        invalidate()
    }

    fun updateBoard(boardData: Array<IntArray>?) {
        boardData?.let {
            cellsData = boardData
            invalidate()
        }
    }

    fun registerListener(listener: OnClickListener) {
        this.listener = listener
    }

    interface OnClickListener {
        fun onClick(position: Position)
    }
}