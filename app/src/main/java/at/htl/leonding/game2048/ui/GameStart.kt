package at.htl.leonding.game2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.leonding.game2048.ui.theme.GameBackground
import at.htl.leonding.game2048.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(viewModel: GameViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(GameBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "2048",
            color = Color.White,
            fontSize = 25.sp
        )
        TextField(
            modifier = Modifier.padding(35.dp),
            value = viewModel.name.value,
            onValueChange = {value -> viewModel.name.value = value},
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = GameBackground
            ),
            label = {
                Text(
                    text = "Name"
                )
            }
        )
        Button(
            onClick = { viewModel.startGame() },
            enabled = viewModel.name.value.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.DarkGray,
                containerColor = Color.White
            )
        ) {
            if(viewModel.name.value.isNotEmpty()) {
                Text(
                    text = "submit",
                    color = Color.Black
                )
            } else {
                Text(
                    text = "submit",
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(viewModel = GameViewModel())
}