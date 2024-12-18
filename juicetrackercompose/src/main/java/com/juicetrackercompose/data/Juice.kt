package com.juicetrackercompose.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.juicetrackercompose.R
import com.juicetrackercompose.ui.theme.Orange as OrangeColor
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Juice(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String = "",
    val color: String,
    val rating: Int
)

enum class JuiceColor(val color: Color, @StringRes val label: Int) {
    Red(Color.Red, R.string.red),
    Blue(Color.Blue, R.string.blue),
    Green(Color.Green, R.string.green),
    Cyan(Color.Cyan, R.string.cyan),
    Yellow(Color.Yellow, R.string.yellow),
    Magenta(Color.Magenta, R.string.magenta),
    Orange(OrangeColor, R.string.orange)
}