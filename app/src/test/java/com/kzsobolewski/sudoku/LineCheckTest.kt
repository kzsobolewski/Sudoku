package com.kzsobolewski.sudoku


import com.kzsobolewski.sudoku.main.views.Board
import org.junit.Assert.assertEquals
import org.junit.Test


class LineCheckTest {

    private val board = Board(emptyArray())

    @Test
    fun `lineCheck of (9,8,7,6,5,4,3,1,2) should equal true`() {
        assertEquals(true, board.lineCheck(intArrayOf(9, 8, 7, 6, 5, 4, 3, 1, 2)))
    }

    @Test
    fun `lineCheck of (1,2,3,4,5,6,7,8,9) should equal true`() {
        assertEquals(true, board.lineCheck(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)))
    }

    @Test
    fun `lineCheck of empty list should equal false`() {
        assertEquals(false, board.lineCheck(intArrayOf()))
    }

    @Test
    fun `lineCheck of list with 0 should equal false`() {
        assertEquals(false, board.lineCheck(intArrayOf(0, 2, 3, 4, 5, 6, 7, 8, 9)))
    }

    @Test
    fun `lineCheck of list with only zeros should equal false`() {
        assertEquals(false, board.lineCheck(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)))
    }

    @Test
    fun `lineCheck of list with one duplication should equal false`() {
        assertEquals(false, board.lineCheck(intArrayOf(9, 8, 7, 6, 5, 4, 3, 1, 8)))
    }

}