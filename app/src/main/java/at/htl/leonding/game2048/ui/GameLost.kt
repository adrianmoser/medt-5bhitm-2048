package at.htl.leonding.game2048.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import at.htl.leonding.game2048.viewmodel.GameViewModel

@Composable
fun GameLost(viewModel: GameViewModel) {
    Column {

        Text(
            text = "YOU HAVE LOST THE GAME!",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}