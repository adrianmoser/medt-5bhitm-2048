package at.htl.leonding.game2048.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import at.htl.leonding.game2048.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(viewModel: GameViewModel) {
    Box {
        TextField(value = "", onValueChange = {})
    }
}