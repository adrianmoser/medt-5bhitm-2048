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
    fun handleSwipe(direction: Direction) {
        Log.d("Direction log", "$direction")
        _gameBoard.value = mutableListOf();

        _gameBoard.value = when (direction) {
            Direction.RIGHT -> model.moveRight()
            Direction.LEFT -> model.moveLeft()
            Direction.DOWN -> model.moveDown()
            Direction.UP -> model.moveUp()
            else -> {
                model.gameBoard
            }
        }
        model.replaceRandomFieldWithTwo()
    }
}