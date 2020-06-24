package com.kzsobolewski.sudoku.main

import android.app.Application
import com.kzsobolewski.sudoku.main.di.mainModule
import com.kzsobolewski.sudoku.net.di.netModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SudokuApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SudokuApplication)
            modules(listOf(mainModule, netModule))
        }
    }
}