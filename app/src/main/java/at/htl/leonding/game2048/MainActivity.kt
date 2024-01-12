package at.htl.leonding.game2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import at.htl.leonding.game2048.model.Direction
import at.htl.leonding.game2048.model.GameState
import at.htl.leonding.game2048.ui.GameBoard
import at.htl.leonding.game2048.ui.GameWon
import at.htl.leonding.game2048.ui.StartScreen
import at.htl.leonding.game2048.ui.theme.Game2048Theme
import at.htl.leonding.game2048.viewmodel.GameViewModel
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    var lazyViewModel = viewModels<GameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewModel = lazyViewModel.value
        setContent {
            Game2048Theme {
                when(viewModel.gameState.value) {
                    GameState.LOST -> Text(text = "GAME LOST!")
                    GameState.RUNNING -> GameBoard(viewModel = viewModel)
                    GameState.WON -> GameWon(viewModel = viewModel)
                    GameState.START -> StartScreen(viewModel = viewModel)
                }
                // A surface container using the 'background' color from the theme
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