package com.kzsobolewski.sudoku.net.di

import com.kzsobolewski.sudoku.main.viewModels.GameplayViewModel
import com.kzsobolewski.sudoku.net.SudokuBoardRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val netModule = module {
    single { SudokuBoardRepository() }
}