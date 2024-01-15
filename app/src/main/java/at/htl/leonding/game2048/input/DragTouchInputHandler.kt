package at.htl.leonding.game2048.input

import at.htl.leonding.game2048.model.Direction
import kotlin.math.abs

class DragTouchInputHandler {


    var xTotal = 0.0
    var yTotal = 0.0

    fun addDragEvent(x: Float, y: Float) {
        this.xTotal += x
        this.yTotal += y
    }

    fun calcDirection(): Direction {
        val absX = abs(xTotal)
        val absY = abs(yTotal)

        //bottom right
        if(xTotal >= 0 && yTotal >= 0) {
            return when {
                absX > absY -> Direction.RIGHT
                absX < absY -> Direction.DOWN
                else -> Direction.NO_DIRECTION
            }
        }

        //top right
        if(xTotal > 0 && yTotal < 0) {
            return when {
                absX > absY -> Direction.RIGHT
                absX < absY -> Direction.UP
                else -> Direction.NO_DIRECTION
            }
        }

        //bottom left
        if(xTotal <= 0 && yTotal >= 0 ) {
            return when {
                absX > absY -> Direction.LEFT
                absX < absY -> Direction.DOWN
                else -> Direction.NO_DIRECTION
            }
        }

        //top left
        if(xTotal < 0 && yTotal < 0) {
            return when {
                absX > absY -> Direction.LEFT
                absX < absY -> Direction.UP
                else -> Direction.NO_DIRECTION
            }
        }

        return Direction.NO_DIRECTION
    }

    fun reset() {
        yTotal = 0.0
        xTotal = 0.0
    }

}