package at.htl.leonding.game2048

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import at.htl.leonding.game2048.model.Direction
import at.htl.leonding.game2048.ui.theme.Game2048Theme
import at.htl.leonding.game2048.ui.theme.GameBackground
import at.htl.leonding.game2048.viewmodel.GameViewModel
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    var viewModel = viewModels<GameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game2048Theme {
                var direction by remember { mutableStateOf(Direction.NO_DIRECTION) }
                // A surface container using the 'background' color from the theme
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
                                    Log.d("Swap Direction", "$direction")
                                })
                        }

                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        viewModel.value.gameBoard.forEach { listOfCells ->
                            Row {
                                listOfCells.forEach { cell ->
                                    GameCell(number = cell)

                                }
                            }

                        }
                    }


                }
            }

        }
    }
}

fun handleDragEvent(x: Float, y: Float): Direction {

    val offset = 5
    if (abs(x) > abs(y)) {
        return when {
            x > offset -> Direction.RIGHT
            x < offset -> Direction.LEFT
            else -> Direction.NO_DIRECTION

        }
    } else {
        return when {
            y > 0 -> Direction.DOWN
            y < 0 -> Direction.UP
            else -> Direction.NO_DIRECTION


        }
    }
}