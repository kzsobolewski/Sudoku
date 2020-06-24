package com.kzsobolewski.sudoku.main.di

import com.kzsobolewski.sudoku.main.viewModels.GameplayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { GameplayViewModel(get()) }
}