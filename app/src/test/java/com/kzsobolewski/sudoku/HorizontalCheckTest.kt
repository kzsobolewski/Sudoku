package com.kzsobolewski.sudoku

import com.kzsobolewski.sudoku.main.viewModels.ApiResponseMapper
import com.kzsobolewski.sudoku.main.views.Board
import com.kzsobolewski.sudoku.main.views.Cell
import org.junit.Before
import org.junit.Test

class HorizontalCheckTest {

    private lateinit var board: Board

    @Before
    fun initBoard() {
        board = Board(Array(Board.BOARD_SIZE) {
            Array(Board.BOARD_SIZE) {
                Cell()
            }
        })
    }

    @Test
    fun `test1`() {
        val array = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        )

        ApiResponseMapper.sudokuApiResponseToBoard(Map<String,Array<IntArray>>("board", array))

    }
}