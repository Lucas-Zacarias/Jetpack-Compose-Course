package com.unscramble.ui

data class UnscrambleUiState(
    val currentScrambleWord: String = "",
    val currentWordCount: Int = 1,
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val isGameOver: Boolean = false
)
