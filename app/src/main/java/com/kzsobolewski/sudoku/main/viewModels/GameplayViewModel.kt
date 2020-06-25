package com.kzsobolewski.sudoku.main.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.sudoku.main.views.Board
import com.kzsobolewski.sudoku.main.views.CellType
import com.kzsobolewski.sudoku.main.views.Position
import com.kzsobolewski.sudoku.net.SudokuBoardRepository
import com.kzsobolewski.sudoku.net.SudokuDifficulty
import kotlinx.coroutines.launch


class GameplayViewModel(private val repository: SudokuBoardRepository) : ViewModel() {

    val board = MutableLiveData<Board>()

    fun updatePosition(position: Position) {
        if (board.value?.getCellType(position) != CellType.FIXED) {
            val newBoard = board.value
            newBoard?.currentPosition = position
            board.postValue(newBoard)
        }
    }

    fun changeCellValue(digit: Int) {
        val newBoard = board.value
        newBoard?.changeCurrentCellValue(digit)
        board.postValue(newBoard)
    }

    fun validateBoard() {
        if (board.value?.validate9x9() == true)
            ;
        else
        ;
    }

    init {
        try {
            viewModelScope.launch {
                val response = repository.getBoard(SudokuDifficulty.easy)
                val newBoard = ApiResponseMapper.sudokuApiResponseToBoard(response)
                board.postValue(newBoard)
            }
        } catch (e: Exception) {
            Log.e(GameplayViewModel::class.simpleName, e.localizedMessage, e)
        }
    }
}
