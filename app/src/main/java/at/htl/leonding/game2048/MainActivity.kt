package at.htl.leonding.game2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.htl.leonding.game2048.ui.theme.Game2048Theme
import at.htl.leonding.game2048.ui.theme.GameBackground
import at.htl.leonding.game2048.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    var viewModel = viewModels<GameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game2048Theme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GameBackground)
                        .padding(16.dp)
                ) {

                    Column {
                        viewModel.value.gameBoard.forEach { listOfCells ->
                            Row {
                                listOfCells.forEach { cell ->
                                    GameCell(number = cell)

                                }
                            }

                        }
                    }

                    GameCell(number = 2)
                    GameCell(number = 3)

                }
            }
        }
    }
}