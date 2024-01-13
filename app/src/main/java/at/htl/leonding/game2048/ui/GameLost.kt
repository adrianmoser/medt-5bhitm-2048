package at.htl.leonding.game2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.leonding.game2048.viewmodel.GameViewModel

@Composable
fun GameLost(viewModel: GameViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xffc90000)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "YOU HAVE LOST THE GAME!",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 25.sp
        )
        Text(
            text = "Username: " + viewModel.name.value,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Button(
            onClick = { viewModel.playAgain() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = "Retry",
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun GameLostPreview() {
    GameLost(viewModel = GameViewModel())
}