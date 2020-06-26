package com.kzsobolewski.sudoku.main.models.board

import com.kzsobolewski.sudoku.main.models.Board
import com.kzsobolewski.sudoku.main.models.Cell
import com.kzsobolewski.sudoku.main.utils.ApiResponseMapper
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VerticalCheckTest {

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
    fun `verticalCheck of proper array should return true`() {
        val array = arrayOf(
            IntArray(9) { 1 },
            IntArray(9) { 2 },
            IntArray(9) { 3 },
            IntArray(9) { 4 },
            IntArray(9) { 5 },
            IntArray(9) { 6 },
            IntArray(9) { 7 },
            IntArray(9) { 8 },
            IntArray(9) { 9 }
        )
        board = ApiResponseMapper.sudokuApiResponseToBoard(mapOf("board" to array))
        assertEquals(true, board.verticalCheck())
    }

    @Test
    fun `verticalCheck() of an array with duplicates should return false`() {
        val array = arrayOf(
            intArrayOf(1, 2, 1, 1, 1, 3, 1, 1, 1),
            IntArray(9) { 2 },
            IntArray(9) { 3 },
            IntArray(9) { 4 },
            IntArray(9) { 5 },
            IntArray(9) { 6 },
            IntArray(9) { 7 },
            IntArray(9) { 8 },
            IntArray(9) { 9 }
        )
        board = ApiResponseMapper.sudokuApiResponseToBoard(mapOf("board" to array))
        assertEquals(false, board.verticalCheck())
    }

    @Test
    fun `verticalCheck() of an array with zeros should return false`() {
        val array = arrayOf(
            intArrayOf(1, 0, 1, 1, 1, 1, 1, 1, 0),
            IntArray(9) { 2 },
            IntArray(9) { 3 },
            IntArray(9) { 4 },
            IntArray(9) { 5 },
            IntArray(9) { 6 },
            IntArray(9) { 7 },
            IntArray(9) { 8 },
            IntArray(9) { 9 }
        )
        board = ApiResponseMapper.sudokuApiResponseToBoard(mapOf("board" to array))
        assertEquals(false, board.verticalCheck())
    }
}