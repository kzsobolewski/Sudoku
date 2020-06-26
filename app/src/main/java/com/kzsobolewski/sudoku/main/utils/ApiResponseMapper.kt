package com.kzsobolewski.sudoku.main.utils

import com.kzsobolewski.sudoku.main.models.Board
import com.kzsobolewski.sudoku.main.models.Cell
import com.kzsobolewski.sudoku.net.SudokuApiResponse


class ApiResponseMapper {

    companion object {

        fun sudokuApiResponseToBoard(input: SudokuApiResponse): Board {
            val board = Board(
                Array(Board.BOARD_SIZE) {
                    Array(Board.BOARD_SIZE) {
                        Cell()
                    }
                })
            val inputArray = input.values.first()
            (0 until Board.BOARD_SIZE).forEach { row ->
                (0 until Board.BOARD_SIZE).forEach { column ->
                    board.cells[row][column].let { cell ->
                        cell.value = inputArray[row][column]
                        if (cell.value != 0)
                            cell.type = CellType.FIXED
                    }
                }
            }
            return board
        }

    }

}