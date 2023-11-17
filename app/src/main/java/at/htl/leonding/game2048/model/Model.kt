package at.htl.leonding.game2048.model

import androidx.compose.runtime.mutableStateListOf

class Model {
    private var gameCells = mutableListOf(
        mutableStateListOf(8, 0, 0, 0),
        mutableStateListOf(0, 2048, 0, 0),
        mutableStateListOf(0, 0, 64, 0),
        mutableStateListOf(0, 16, 0, 0)
    )

    val gameBoard: List<List<Int>>
        get() = this.gameCells.toList()

}