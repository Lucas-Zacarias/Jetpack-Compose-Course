package com.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bookshelf.R

@Composable
fun BookshelfApp() {
    Scaffold(
        topBar = {
            BookshelfTopBar()
        }
    ) {
        val viewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
        BookshelfHome(
            bookshelfUiState = viewModel.uiState,
            retryEvent = viewModel::getBooks,
            modifier = Modifier.fillMaxSize(),
            paddingValues = it
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookshelfTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.inversePrimary
        )
    )
}
