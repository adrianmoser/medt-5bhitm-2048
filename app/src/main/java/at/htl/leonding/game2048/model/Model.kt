package at.htl.leonding.game2048.model


class Model {
    private var gameCells = mutableListOf(
        mutableListOf(8, 8, 0, 0),
        mutableListOf(0, 2048, 0, 0),
        mutableListOf(0, 64, 64, 0),
        mutableListOf(16, 16, 0, 0)
    )

    private val sizeOfTheRow = 4;

    val gameBoard: List<List<Int>>
        get() = this.gameCells.toList()

    fun reverse() {
        gameCells.forEach { it.reverse() }
    }

    fun shiftLeft() {
        for (i in 0 until gameCells.size) {
            val cellRow = gameCells[i]
            val nonZeroValues = cellRow.filter { it != 0 }.toMutableList()
            for (i in 0 until nonZeroValues.size - 1) {
                if (nonZeroValues[i] == nonZeroValues[i + 1]) {
                    // Kombiniere Kacheln mit gleichem Wert
                    nonZeroValues[i] *= 2
                    nonZeroValues.removeAt(i + 1)
                }
            }

            nonZeroValues += List(sizeOfTheRow - nonZeroValues.size) { 0 }
            gameCells[i] = nonZeroValues
        }


    }

    fun moveRight(): List<List<Int>> {
        reverse()
        shiftLeft()
        reverse()
        return gameCells
    }

    fun moveLeft(): List<List<Int>> {
        shiftLeft()
        return gameCells
    }

    fun moveDown(): List<List<Int>> {
        return gameCells
    }

    fun moveUp(): List<List<Int>> {
        return gameCells
    }

}