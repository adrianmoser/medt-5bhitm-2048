package at.htl.leonding.game2048.viewmodel

import androidx.lifecycle.ViewModel
import at.htl.leonding.game2048.model.Model

class GameViewModel : ViewModel() {
    private val model = Model()
    val gameBoard: List<List<Int>>
        get() = model.gameBoard

}