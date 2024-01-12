package at.htl.leonding.game2048.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.leonding.game2048.viewmodel.GameViewModel

@Composable
fun GameWon(
    viewModel: GameViewModel
) {
    Column {
        Text(
            text = "YOU HAVE WON THE GAME!",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Username: " + viewModel.name.value,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun GameWonPreview() {
    GameWon(viewModel = GameViewModel())
}