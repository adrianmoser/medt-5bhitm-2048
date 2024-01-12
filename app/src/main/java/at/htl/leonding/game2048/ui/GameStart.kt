package at.htl.leonding.game2048.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import at.htl.leonding.game2048.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(viewModel: GameViewModel) {
    Column {
        TextField(value = viewModel.name.value, onValueChange = {value -> viewModel.name.value = value})
        Button(
            onClick = { viewModel.startGame() },
            enabled = viewModel.name.value.isNotEmpty()
        ) {
            Text(text = "submit")
        }
    }
}