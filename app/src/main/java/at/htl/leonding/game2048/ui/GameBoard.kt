package at.htl.leonding.game2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Score: " + viewModel.score.value,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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

@Preview
@Composable
fun GameBoardPreview() {
    GameBoard(viewModel = GameViewModel())
}