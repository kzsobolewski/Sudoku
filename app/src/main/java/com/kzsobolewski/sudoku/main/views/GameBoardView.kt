package com.kzsobolewski.sudoku.main.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.kzsobolewski.sudoku.main.utils.ViewPaints
import com.kzsobolewski.sudoku.main.models.Board
import com.kzsobolewski.sudoku.main.models.Cell
import com.kzsobolewski.sudoku.main.utils.CellType

typealias Position = Pair<Int, Int>

class GameBoardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val viewPaints =
        ViewPaints(resources)
    private var cellSizePixels = 0f
    private lateinit var listener: OnClickListener

    private var viewBoard: Board? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = measuredHeight.coerceAtMost(measuredWidth)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        cellSizePixels = (width / Board.BOARD_SIZE).toFloat()
        if (viewBoard != null && canvas != null) {
            setCellRects()
            drawLines(canvas)
            fillCells(canvas)
        }
    }

    private fun setCellRects() {
        (0 until Board.BOARD_SIZE).forEach { row ->
            (0 until Board.BOARD_SIZE).forEach { column ->
                viewBoard?.cells?.get(row)?.get(column)?.rect = Rect(
                    row * cellSizePixels.toInt(),
                    column * cellSizePixels.toInt(),
                    (row + 1) * cellSizePixels.toInt(),
                    (column + 1) * cellSizePixels.toInt()
                )
            }
        }
    }

    private fun drawLines(canvas: Canvas) {
        (1 until Board.BOARD_SIZE).forEach {
            if (it % Board.BOX_SIZE == 0) {
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

    private fun fillCells(canvas: Canvas) {
        (0 until Board.BOARD_SIZE).forEach { row ->
            (0 until Board.BOARD_SIZE).forEach { column ->
                viewBoard?.cells?.get(row)?.get(column)?.let { cell ->
                    drawSelectedCell(canvas, row to column, cell)
                    drawTextInsideRectangle(canvas, cell.rect, cell.value)
                }
            }
        }
    }

    private fun drawSelectedCell(canvas: Canvas, position: Position, cell: Cell) {
        val paintToUse = when {
            cell.type == CellType.FIXED -> viewPaints.fixedCirclePaint
            position == viewBoard?.currentPosition -> viewPaints.selectedCirclePaint
            else -> viewPaints.circlePaint
        }
        drawCell(canvas, cell.rect, paintToUse)
    }


    private fun drawCell(canvas: Canvas, rect: Rect, paint: Paint) {
        val cellMargin = 8
        canvas.drawCircle(
            rect.exactCenterX(),
            rect.exactCenterY(),
            cellSizePixels / 2 - cellMargin,
            paint
        )
    }

    private fun drawTextInsideRectangle(canvas: Canvas, rect: Rect, digit: Int) {
        if (digit != 0) {
            val str = digit.toString()
            val xOffset = viewPaints.textPaint.measureText(str) * 0.5f
            val yOffset = viewPaints.textPaint.fontMetrics.ascent * -0.4f
            val textX = rect.exactCenterX() - xOffset
            val textY = rect.exactCenterY() + yOffset
            canvas.drawText(str, textX, textY, viewPaints.textPaint)
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

    fun updateBoard(board: Board?) {
        viewBoard = board
        invalidate()
    }

    fun registerListener(listener: OnClickListener) {
        this.listener = listener
    }

    interface OnClickListener {
        fun onClick(position: Position)
    }
}