package com.kzsobolewski.sudoku

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameBoardView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val line = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), line)
    }


}