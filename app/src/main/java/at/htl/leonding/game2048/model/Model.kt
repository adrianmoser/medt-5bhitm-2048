package at.htl.leonding.game2048.model

import androidx.compose.runtime.mutableStateListOf

class Model {
    private var gameCells = mutableListOf(
        mutableStateListOf(8, 0, 0, 0),
        mutableStateListOf(0, 2048, 0, 0),
        mutableStateListOf(0, 0, 64, 0),
        mutableStateListOf(0, 16, 0, 0)
    )

    private val sizeOfTheRow = 4;

    val gameBoard: List<List<Int>>
        get() = this.gameCells.toList()

    fun reverse() {
        gameCells.reverse()
    }

    fun shiftLeft() {
        var test = gameCells.map { cellRow ->
            {
                val nonZeroValues = cellRow.filter { it != 0 }.toMutableList()
                for (i in 0 until nonZeroValues.size - 1) {
                    if (nonZeroValues[i] == nonZeroValues[i + 1]) {
                        // Kombiniere Kacheln mit gleichem Wert
                        nonZeroValues[i] *= 2
                        nonZeroValues.removeAt(i + 1)
                    }
                }

                // Fülle den Rest der Zeile mit Nullen auf
                nonZeroValues += List(sizeOfTheRow - nonZeroValues.size) { 0 }


            }
        }
        tes
    }

    fun moveRight(): List<List<Int>> {
        reverse()
        return gameCells
    }

    fun moveLeft(): List<List<Int>> {
        return gameCells
    }

    fun moveDown(): List<List<Int>> {
        return gameCells
    }

    fun moveUp(): List<List<Int>> {
        return gameCells
    }

}