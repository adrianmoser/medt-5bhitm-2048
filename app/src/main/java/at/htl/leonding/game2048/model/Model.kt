package at.htl.leonding.game2048.model

import kotlin.random.Random


class Model {
    private var gameCells = mutableListOf(
        mutableListOf(8, 8, 0, 0),
        mutableListOf(0, 2048, 0, 0),
        mutableListOf(0, 64, 64, 0),
        mutableListOf(16, 16, 0, 0)
    )

    private val sizeOfTheRow = 4;

    private val isGameOver = false

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

    fun rotateMatrix90Degrees() {
        val numRows = gameCells.size
        val numCols = gameCells[0].size

        val rotatedMatrix = MutableList(numCols) { MutableList(numRows) { 0 } }

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                rotatedMatrix[j][numRows - 1 - i] = gameCells[i][j]
            }
        }

        gameCells = rotatedMatrix;
    }

    fun replaceRandomFieldWithTwo() {
        // Find all positions with zero value
        val zeroPositions = mutableListOf<Pair<Int, Int>>()

        for (i in gameCells.indices) {
            for (j in gameCells[i].indices) {
                if (gameCells[i][j] == 0) {
                    zeroPositions.add(Pair(i, j))
                }
            }
        }

        // Choose a random position with zero value
        if (zeroPositions.isNotEmpty()) {
            val randomPosition = zeroPositions[Random.nextInt(zeroPositions.size)]
            val (row, col) = randomPosition

            // Replace the chosen position with 2
            gameCells[row][col] = 2
        }
    }

    //region move
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
        rotateMatrix90Degrees()
        shiftLeft()
        rotateMatrix90Degrees()
        rotateMatrix90Degrees()
        rotateMatrix90Degrees()
        return gameCells
    }

    fun moveUp(): List<List<Int>> {
        rotateMatrix90Degrees()
        rotateMatrix90Degrees()
        rotateMatrix90Degrees()
        shiftLeft()
        rotateMatrix90Degrees()
        return gameCells
    }
    //endregion

}