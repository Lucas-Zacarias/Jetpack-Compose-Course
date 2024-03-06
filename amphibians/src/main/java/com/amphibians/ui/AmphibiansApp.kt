package com.amphibians.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AmphibiansApp() {
    Scaffold {
        val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
        AmphibiansHome(
            uiState = viewModel.uiState,
            contentPadding = it
        )
    }
}
