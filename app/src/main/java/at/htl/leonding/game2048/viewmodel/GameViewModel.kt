package at.htl.leonding.game2048.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import at.htl.leonding.game2048.model.Direction
import at.htl.leonding.game2048.model.Model

class GameViewModel : ViewModel() {
    private val model = Model()

    /*val gameBoard: List<List<Int>>
        get() = model.gameBoard
*/
    private var _gameBoard = mutableStateOf(model.gameBoard)
    val gameBoard = _gameBoard
    private var _gameState = mutableStateOf(model.gameState)
    val gameState = _gameState
    fun handleSwipe(direction: Direction) {
        Log.d("Direction log", "$direction")
        _gameBoard.value = mutableListOf();

        model.move(direction)
        _gameBoard.value = model.gameBoard;
    }
}