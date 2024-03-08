package com.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amphibians.R

@Composable
fun AmphibiansApp() {
    Scaffold (
        topBar = {
            AmphibiansTopBar()
        }
    )
    {
        val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
        AmphibiansHome(
            uiState = viewModel.uiState,
            retryEvent = viewModel::getAmphibians,
            contentPadding = it,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AmphibiansTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge)
        }
    )
}
