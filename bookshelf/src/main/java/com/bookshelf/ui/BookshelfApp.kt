package com.bookshelf.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bookshelf.R

@Composable
fun BookshelfApp() {
    val viewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
    Scaffold(
        topBar = {
            BookshelfTopBar(
                eventResetUiStateFromStateTitleNotFound = viewModel::resetUiStateFromStateTitleNotFound,
                eventGetBooksByName = {
                    viewModel.getBooksByName(it)
                }
            )
        }
    ) {
        BookshelfHome(
            bookshelfUiState = viewModel.uiState,
            retryEvent = viewModel::getBooks,
            modifier = Modifier.fillMaxSize(),
            paddingValues = it
        )
    }
}

@Composable
fun CustomSearch(
    eventBackFromSearch: () -> Unit,
    eventSearchBooksByName: (String) -> Unit,
    eventResetUiStateFromStateTitleNotFound: () -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember {
        mutableStateOf("")
    }
    TextField(
        value = text,
        onValueChange = {
            text = it
            eventSearchBooksByName(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surface,
                modifier = Modifier.clickable { eventBackFromSearch() })
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        text = ""
                        eventResetUiStateFromStateTitleNotFound()
                    },
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.surface,
            focusedTextColor = MaterialTheme.colorScheme.surface,
            unfocusedTextColor = MaterialTheme.colorScheme.surface,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        ),
        placeholder = {
            Text(text = stringResource(R.string.search_book))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookshelfTopBar(
    eventGetBooksByName: (String) -> Unit,
    eventResetUiStateFromStateTitleNotFound: () -> Unit,
) {
    var showSearchTopBar by remember {
        mutableStateOf(false)
    }

    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.surface
        ),
        actions = {
            if (showSearchTopBar) {
                CustomSearch(
                    eventBackFromSearch = { showSearchTopBar = false },
                    eventSearchBooksByName = { eventGetBooksByName(it) },
                    eventResetUiStateFromStateTitleNotFound = eventResetUiStateFromStateTitleNotFound,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .clickable {
                            showSearchTopBar = true
                        },
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        }
    )

}
