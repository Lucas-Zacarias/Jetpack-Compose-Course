package com.amphibians.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AmphibiansHome(
    uiState: AmphibiansUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    if(uiState is AmphibiansUiState.Success) {

            Text(
                text =
                "Anfibios encontrados: ${uiState.amphibians.size}",
                modifier = modifier.fillMaxSize().padding(contentPadding)
            )
    }
}