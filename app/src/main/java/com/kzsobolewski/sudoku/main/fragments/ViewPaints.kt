package com.kzsobolewski.sudoku.main.fragments

import android.content.res.Resources
import android.graphics.Paint
import androidx.core.content.res.ResourcesCompat
import com.kzsobolewski.sudoku.R


class ViewPaints(private val resources: Resources) {

    private val fontSize = 100f

    val linePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 4f
        color = ResourcesCompat.getColor(
            resources,
            R.color.colorLine, null
        )
    }

    val circlePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 4f
        color = ResourcesCompat.getColor(
            resources,
            R.color.colorPrimary, null
        )
    }

    val selectedCirclePaint = Paint().apply {
        style = Paint.Style.FILL
        color = ResourcesCompat.getColor(
            resources,
            R.color.colorPrimary, null
        )
    }

    val fixedCirclePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 4f
        color = ResourcesCompat.getColor(
            resources,
            R.color.colorCircleInline, null
        )
    }

    val textPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = ResourcesCompat.getColor(
            resources,
            R.color.colorDigit, null
        )
        textSize = fontSize
    }
}