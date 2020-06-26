package com.kzsobolewski.sudoku.net

import retrofit2.http.GET
import retrofit2.http.Query

typealias SudokuApiResponse = Map<String, Array<IntArray>>

interface SudokuBoardApi {

    @GET("/board")
    suspend fun getBoard(@Query("difficulty") difficulty: SudokuDifficulty): SudokuApiResponse?

}