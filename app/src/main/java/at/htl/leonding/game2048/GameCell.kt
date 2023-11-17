package at.htl.leonding.game2048

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import at.htl.leonding.game2048.ui.theme.Game1024Background
import at.htl.leonding.game2048.ui.theme.Game128Background
import at.htl.leonding.game2048.ui.theme.Game16Background
import at.htl.leonding.game2048.ui.theme.Game2048Background
import at.htl.leonding.game2048.ui.theme.Game256Background
import at.htl.leonding.game2048.ui.theme.Game2Color
import at.htl.leonding.game2048.ui.theme.Game32Background
import at.htl.leonding.game2048.ui.theme.Game4Color
import at.htl.leonding.game2048.ui.theme.Game512Background
import at.htl.leonding.game2048.ui.theme.Game64Background
import at.htl.leonding.game2048.ui.theme.Game8Color
import at.htl.leonding.game2048.ui.theme.GameCellBackground

@Composable
fun GameCell(number: Number) {
    Box (
        modifier = Modifier.background(setColorOfCell(number))
    ) {
        Text(text = number.toString())
    }
}

fun setColorOfCell(number: Number): Color{
    return when (number) {
        2 -> Game2Color
        4 -> Game4Color
        8 -> Game8Color
        16 -> Game16Background
        32 -> Game32Background
        64 -> Game64Background
        128 -> Game128Background
        256 -> Game256Background
        512 -> Game512Background
        1024 -> Game1024Background
        2048 -> Game2048Background
        else -> GameCellBackground
    }
}