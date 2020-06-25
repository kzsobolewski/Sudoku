package com.kzsobolewski.sudoku.main.views

class Board(var cells: Array<Array<Cell>>) {

    var currentPosition: Position = -1 to -1

    fun changeCurrentCellValue(digit: Int) {
        if (isCurrentSelectionInBounds())
            cells[currentPosition.first][currentPosition.second].value = digit
    }

    fun validate9x9(): Boolean {
        // TODO
        return false
    }

    fun getCellType(position: Position): CellType =
        cells[position.first][position.second].type


    fun horizontalCheck(): Boolean {
        return cells.none { row ->
            lineCheck(row.map { cell ->
                cell.value
            })
        }
    }

    fun lineCheck(list: List<Int>): Boolean {
        return list.filter { it in 1..9 }.distinct().size == 9
    }

    private fun isCurrentSelectionInBounds(): Boolean {
        return currentPosition.first in 0 until BOARD_SIZE &&
                currentPosition.second in 0 until BOARD_SIZE
    }

    companion object {
        const val BOX_SIZE = 3
        const val BOARD_SIZE = 9
    }
}