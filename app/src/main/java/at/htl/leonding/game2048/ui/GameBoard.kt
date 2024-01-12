package at.htl.leonding.game2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import at.htl.leonding.game2048.GameCell
import at.htl.leonding.game2048.handleDragEvent
import at.htl.leonding.game2048.model.Direction
import at.htl.leonding.game2048.ui.theme.GameBackground
import at.htl.leonding.game2048.viewmodel.GameViewModel

@Composable
fun GameBoard(viewModel: GameViewModel) {
    var direction by remember { mutableStateOf(Direction.NO_DIRECTION) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GameBackground)
            .padding(16.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (x, y) = dragAmount
                        direction = handleDragEvent(x, y)
                    },
                    onDragEnd = {
                        viewModel.handleSwipe(direction)
                    })
            }

    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            viewModel.gameBoard.value.forEach { listOfCells ->
                Row {
                    listOfCells.forEach { cell ->
                        GameCell(number = cell)

                    }
                }

            }
        }


    }
}