package com.kzsobolewski.sudoku.main.models

import android.graphics.Rect
import com.kzsobolewski.sudoku.main.utils.CellType
import com.kzsobolewski.sudoku.main.views.Position

data class Cell(
    var value: Int = 0,
    var type: CellType = CellType.EMPTY,
    var position: Position = -1 to -1,
    var rect: Rect = Rect()
)