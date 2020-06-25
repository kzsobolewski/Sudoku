package com.kzsobolewski.sudoku

import com.kzsobolewski.sudoku.main.viewModels.ApiResponseMapper
import com.kzsobolewski.sudoku.main.views.Board
import com.kzsobolewski.sudoku.main.views.Cell
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BoxCheckTest {

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
    fun `boxCheck() of proper array should return true`() {
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
        assertEquals(true, board.boxCheck())
    }

    @Test
    fun `boxCheck() of an array with duplicates should return false`() {
        val array = arrayOf(
            intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            intArrayOf(4, 2, 6, 4, 5, 6, 4, 5, 6),
            intArrayOf(7, 2, 9, 7, 8, 9, 7, 8, 9),
            intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            intArrayOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            intArrayOf(7, 8, 9, 7, 8, 9, 7, 8, 9),
            intArrayOf(1, 2, 3, 1, 2, 3, 1, 5, 3),
            intArrayOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            intArrayOf(7, 8, 9, 7, 8, 9, 7, 5, 9)
        )
        board = ApiResponseMapper.sudokuApiResponseToBoard(mapOf("board" to array))
        assertEquals(false, board.boxCheck())
    }

}
