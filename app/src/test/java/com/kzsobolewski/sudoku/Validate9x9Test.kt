package com.kzsobolewski.sudoku

import com.kzsobolewski.sudoku.main.viewModels.ApiResponseMapper
import com.kzsobolewski.sudoku.main.views.Board
import com.kzsobolewski.sudoku.main.views.Cell
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class Validate9x9Test {

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
    fun `validate9x9() of wrong solved sudoku board should return false`() {
        val array = arrayOf(
            intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            intArrayOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            intArrayOf(7, 8, 9, 7, 8, 9, 7, 8, 9),
            intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            intArrayOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            intArrayOf(7, 8, 9, 7, 8, 9, 7, 8, 9),
            intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            intArrayOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            intArrayOf(7, 8, 9, 7, 8, 9, 7, 8, 9)
        )
        board = ApiResponseMapper.sudokuApiResponseToBoard(mapOf("board" to array))
        assertEquals(false, board.validate9x9())
    }

    @Test
    fun `validate9x9() of properly solved sudoku board should return true`() {
        val array = arrayOf(
            intArrayOf(2, 1, 5, 3, 7, 9, 8, 6, 4),
            intArrayOf(9, 8, 6, 1, 2, 4, 3, 5, 7),
            intArrayOf(7, 3, 4, 8, 5, 6, 2, 1, 9),
            intArrayOf(4, 5, 2, 7, 8, 1, 6, 9, 3),
            intArrayOf(8, 6, 9, 5, 4, 3, 1, 7, 2),
            intArrayOf(3, 7, 1, 6, 9, 2, 4, 8, 5),
            intArrayOf(5, 2, 7, 4, 1, 8, 9, 3, 6),
            intArrayOf(6, 4, 8, 9, 3, 7, 5, 2, 1),
            intArrayOf(1, 9, 3, 2, 6, 5, 7, 4, 8)
        )
        board = ApiResponseMapper.sudokuApiResponseToBoard(mapOf("board" to array))
        assertEquals(true, board.validate9x9())
    }
}
