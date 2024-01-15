package at.htl.leonding.game2048.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import at.htl.leonding.game2048.model.Direction
import at.htl.leonding.game2048.model.Model

class GameViewModel : ViewModel() {
    private val model = Model()

    private var _gameBoard = mutableStateOf(model.gameBoard)
    val gameBoard = _gameBoard
    private var _gameState = mutableStateOf(model.gameState)
    val gameState = _gameState
    val name = mutableStateOf(model.name)
    val score = mutableStateOf(model.score)

    fun handleSwipe(direction: Direction) {
        Log.d("Direction log", "$direction")
        _gameBoard.value = mutableListOf();

        model.move(direction)
        _gameBoard.value = model.gameBoard;
        this.score.value = model.score


        Handler(Looper.getMainLooper()).postDelayed({
            this.gameState.value = model.gameState
        },500)
    }

    fun startGame() {
        model.startGame()
        this.score.value = model.score
        this.gameState.value = model.gameState
    }

    fun playAgain() {
        model.playAgain()
        this._gameBoard.value = model.gameBoard
        this.gameState.value = model.gameState
        this.score.value = model.score
    }
}