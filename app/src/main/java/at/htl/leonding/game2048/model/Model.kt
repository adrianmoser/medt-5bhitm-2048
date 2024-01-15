package at.htl.leonding.game2048.model

import kotlin.random.Random


class Model {
    private var gameCells = mutableListOf(
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
    )
    val gameBoard: List<List<Int>>
        get() = this.gameCells.toList()

    private var previousGameBoard: List<List<Int>> = emptyList()

    private val sizeOfTheRow = 4;

    private var _gameState = GameState.START
    val gameState: GameState
        get() = this._gameState

    var name = ""

    private var _score = 0
    val score: Int
        get() = this._score

    init {
        replaceRandomField()
        replaceRandomField()
        replaceRandomField()
    }

    fun startGame() {
        this._gameState = GameState.RUNNING
    }

    fun playAgain() {
        this.gameCells = mutableListOf(
            mutableListOf(0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0),
        )

        replaceRandomField()
        replaceRandomField()
        replaceRandomField()

        _gameState = GameState.RUNNING
        _score = 0;
    }

    private fun reverse() {
        gameCells.forEach { it.reverse() }
    }

    private fun shiftLeft() {
        for (i in 0 until gameCells.size) {
            val cellRow = gameCells[i]
            val nonZeroValues = cellRow.filter { it != 0 }.toMutableList()

            var j = 0
            while (j < nonZeroValues.size - 1) {
                if (nonZeroValues[j] == nonZeroValues[j + 1]) {
                    // Combine tiles with the same value
                    nonZeroValues[j] *= 2
                    _score += nonZeroValues[j];
                    nonZeroValues.removeAt(j + 1)
                } else {
                    j++
                }
            }

            nonZeroValues += List(sizeOfTheRow - nonZeroValues.size) { 0 }
            gameCells[i] = nonZeroValues
        }
    }

    private fun rotateMatrix90Degrees() {
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

    private fun replaceRandomField() {
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

            // Replace the chosen position with 2, or a 10% chance of getting a  4
            gameCells[row][col] = if((0..10).random() == 10) 4 else 2;
        }
    }

    private fun isGameOver(): Boolean {
        val numRows = gameCells.size
        val numCols = gameCells[0].size

        // Check for zeros in the matrix
        if (gameCells.any { row -> row.any { it == 0 } }) {
            return false
        }

        // Check for neighboring cells with the same values
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                val currentValue = gameCells[i][j]

                // Check left neighbor
                if (j > 0 && gameCells[i][j - 1] == currentValue) {
                    return false
                }

                // Check right neighbor
                if (j < numCols - 1 && gameCells[i][j + 1] == currentValue) {
                    return false
                }

                // Check above neighbor
                if (i > 0 && gameCells[i - 1][j] == currentValue) {
                    return false
                }

                // Check below neighbor
                if (i < numRows - 1 && gameCells[i + 1][j] == currentValue) {
                    return false
                }
            }
        }

        // If no zeros and no neighbors with the same value are found, the board is valid
        return true
    }

    private fun hasWon(): Boolean {
        return gameCells.flatten().contains(2048)
    }

    //region move
    fun move(direction: Direction) {
        // Store the current game board state
        previousGameBoard = gameBoard.toList()

        when (direction) {
            Direction.RIGHT -> moveRight()
            Direction.LEFT -> moveLeft()
            Direction.DOWN -> moveDown()
            Direction.UP -> moveUp()
            else -> {
                gameBoard
            }
        }

        if(isGameOver()) {
            _gameState = GameState.LOST;
            return;
        }

        if(hasWon()) {
            _gameState = GameState.WON
            return;
        }

        // Check if the game board has changed
        if (gameBoard != previousGameBoard) {
            if (isGameOver()) {
                _gameState = GameState.LOST
                return
            }

            if (hasWon()) {
                _gameState = GameState.WON
                return
            }

            replaceRandomField()
        }
    }

    private fun moveRight(): List<List<Int>> {
        reverse()
        shiftLeft()
        reverse()
        return gameCells
    }

    private fun moveLeft(): List<List<Int>> {
        shiftLeft()
        return gameCells
    }

    private fun moveDown(): List<List<Int>> {
        rotateMatrix90Degrees()
        shiftLeft()
        rotateMatrix90Degrees()
        rotateMatrix90Degrees()
        rotateMatrix90Degrees()
        return gameCells
    }

    private fun moveUp(): List<List<Int>> {
        rotateMatrix90Degrees()
        rotateMatrix90Degrees()
        rotateMatrix90Degrees()
        shiftLeft()
        rotateMatrix90Degrees()
        return gameCells
    }
    //endregion
}