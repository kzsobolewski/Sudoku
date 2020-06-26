package com.kzsobolewski.sudoku.main.models.board

import com.kzsobolewski.sudoku.main.utils.ApiResponseMapper
import com.kzsobolewski.sudoku.main.models.Board
import com.kzsobolewski.sudoku.main.models.Cell
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HorizontalCheckTest {

    private lateinit var board: Board

    @Before
    fun initBoard() {
        board =
            Board(Array(Board.BOARD_SIZE) {
                Array(Board.BOARD_SIZE) {
                    Cell()
                }
            })
    }

    @Test
    fun `horizontalCheck() of proper array should return true`() {
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
        board = ApiResponseMapper.sudokuApiResponseToBoard(mapOf("board" to array))
        assertEquals(true, board.horizontalCheck())
    }

    @Test
    fun `horizontalCheck() of an array with duplicates should return false`() {
        val array = arrayOf(
            intArrayOf(5, 2, 3, 5, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 1, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        )
        board = ApiResponseMapper.sudokuApiResponseToBoard(mapOf("board" to array))
        assertEquals(false, board.horizontalCheck())
    }

}