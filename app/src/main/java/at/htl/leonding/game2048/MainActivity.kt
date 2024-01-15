package at.htl.leonding.game2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import at.htl.leonding.game2048.model.GameState
import at.htl.leonding.game2048.ui.GameBoard
import at.htl.leonding.game2048.ui.GameLost
import at.htl.leonding.game2048.ui.GameWon
import at.htl.leonding.game2048.ui.StartScreen
import at.htl.leonding.game2048.ui.theme.Game2048Theme
import at.htl.leonding.game2048.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    var lazyViewModel = viewModels<GameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewModel = lazyViewModel.value
        setContent {
            Game2048Theme {
                when(viewModel.gameState.value) {
                    GameState.LOST -> GameLost(viewModel = viewModel)
                    GameState.RUNNING -> GameBoard(viewModel = viewModel)
                    GameState.WON -> GameWon(viewModel = viewModel)
                    GameState.START -> StartScreen(viewModel = viewModel)
                }
                // A surface container using the 'background' color from the theme
            }

        }
    }
}