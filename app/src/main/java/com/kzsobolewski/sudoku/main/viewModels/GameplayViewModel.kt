package com.kzsobolewski.sudoku.main.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.sudoku.main.views.Position
import com.kzsobolewski.sudoku.net.SudokuBoardRepository
import com.kzsobolewski.sudoku.net.SudokuDifficulty
import kotlinx.coroutines.launch


class GameplayViewModel(private val repository: SudokuBoardRepository) : ViewModel() {

    private val initBoard = MutableLiveData<Array<IntArray>>()
    val board = MutableLiveData<Array<IntArray>>()
    val currentPosition = MutableLiveData<Position>()

    fun updatePosition(pos: Position) {
        if (initBoard.value?.get(pos.first)?.get(pos.second) == 0)
            currentPosition.postValue(pos)
    }

    init {
        try {
            viewModelScope.launch {
                val response = repository.getBoard(SudokuDifficulty.easy).values.first()
                initBoard.postValue(response)
                board.postValue(response)
            }
        } catch (e: Exception) {
            Log.e(GameplayViewModel::class.simpleName, e.localizedMessage, e)
        }
    }
}
