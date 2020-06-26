package com.kzsobolewski.sudoku.main.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.sudoku.main.models.Board
import com.kzsobolewski.sudoku.main.utils.ApiResponseMapper
import com.kzsobolewski.sudoku.main.utils.NetworkBoardState
import com.kzsobolewski.sudoku.main.utils.CellType
import com.kzsobolewski.sudoku.main.views.Position
import com.kzsobolewski.sudoku.net.SudokuBoardRepository
import com.kzsobolewski.sudoku.net.SudokuDifficulty
import kotlinx.coroutines.launch


class GameplayViewModel(private val repository: SudokuBoardRepository) : ViewModel() {

    val board = MutableLiveData<Board>()
    val isLoaded = MutableLiveData(NetworkBoardState.LOADING)

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

    fun validateBoard(): Boolean =
        board.value?.validate9x9() == true

    init {
        viewModelScope.launch {
            val response = repository.getBoard(SudokuDifficulty.easy)
            if (response == null) {
                isLoaded.postValue(NetworkBoardState.ERROR)
            } else {
                val newBoard = ApiResponseMapper.sudokuApiResponseToBoard(response)
                board.postValue(newBoard)
                isLoaded.postValue(NetworkBoardState.LOADED)
            }
        }
    }
}
