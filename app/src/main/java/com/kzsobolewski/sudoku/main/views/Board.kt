package com.kzsobolewski.sudoku.main.views

class Board(var cells: Array<Array<Cell>>) {

    var currentPosition: Position = -1 to -1

    fun changeCurrentCellValue(digit: Int) {
        if (isCurrentSelectionInBounds())
            cells[currentPosition.first][currentPosition.second].value = digit
    }

    fun getCellType(position: Position): CellType =
        cells[position.first][position.second].type

    fun validate9x9(): Boolean =
        boxCheck() && horizontalCheck() && verticalCheck()

    fun boxCheck(): Boolean {
        val result = mutableListOf<Int>()

        (0 until BOARD_SIZE step BOX_SIZE).forEach { rowBox ->
            (0 until BOARD_SIZE step BOX_SIZE).forEach { rowColumn ->
                (0 until BOX_SIZE).forEach { row ->
                    (0 until BOX_SIZE).forEach { column ->
                        result.add(cells[row + rowBox][column + rowColumn].value)
                    }
                }
            }
        }
        return result.chunked(BOARD_SIZE).map { it.toIntArray() }.all { lineCheck(it) }
    }


    fun horizontalCheck(): Boolean =
        cells.all { row -> lineCheck(row.map { it.value }.toIntArray()) }

    fun verticalCheck(): Boolean {
        val result = Array(BOARD_SIZE) { IntArray(BOARD_SIZE) { 0 } }
        (0 until BOARD_SIZE).forEach { row ->
            (0 until BOARD_SIZE).forEach { column ->
                result[row][column] = cells[column][row].value
            }
        }
        return result.all { lineCheck(it) }
    }

    fun lineCheck(list: IntArray): Boolean =
        list.filter { it in 1..BOARD_SIZE }.distinct().size == BOARD_SIZE


    private fun isCurrentSelectionInBounds(): Boolean =
        currentPosition.first in 0 until BOARD_SIZE &&
                currentPosition.second in 0 until BOARD_SIZE

    companion object {
        const val BOX_SIZE = 3
        const val BOARD_SIZE = 9
    }
}