package com.kzsobolewski.sudoku.net.di

import com.kzsobolewski.sudoku.net.SudokuBoardRepository
import org.koin.dsl.module

val netModule = module {
    single { SudokuBoardRepository() }
}