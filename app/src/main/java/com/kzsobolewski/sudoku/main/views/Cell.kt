package com.kzsobolewski.sudoku.main.views

import android.graphics.Rect

data class Cell(
    var value: Int = 0,
    var type: CellType = CellType.EMPTY,
    var position: Position = -1 to -1,
    var rect: Rect = Rect()
)