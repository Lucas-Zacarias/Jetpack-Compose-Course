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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bookshelf.R

@Composable
fun BookshelfApp() {
    val viewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
    val bookshelfUiState by viewModel.bookSearchUiState.collectAsState()
    Scaffold(
        topBar = {
            BookshelfTopBar(
                bookSearchUiState = bookshelfUiState,
                currentSearch = viewModel.currentSearch,
                eventShowSearchBar = viewModel::openSearchBar,
                eventHideSearchBar = viewModel::closeSearchBar,
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
private fun CustomSearch(
    currentSearch: String,
    eventResetUiStateFromStateTitleNotFound: () -> Unit,
    eventBackFromSearch: () -> Unit,
    modifier: Modifier = Modifier,
    eventSearchBooksByName: (String) -> Unit
) {
    TextField(
        value = currentSearch,
        onValueChange = {
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
            if (currentSearch.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = null,
                    modifier = Modifier.clickable {
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
    bookSearchUiState: BookSearchUiState,
    currentSearch: String,
    eventHideSearchBar: () -> Unit,
    eventResetUiStateFromStateTitleNotFound: () -> Unit,
    eventGetBooksByName: (String) -> Unit,
    eventShowSearchBar: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.surface
        ),
        actions = {
            if (bookSearchUiState.isSearchBarVisible) {
                CustomSearch(
                    currentSearch = currentSearch,
                    eventResetUiStateFromStateTitleNotFound = eventResetUiStateFromStateTitleNotFound,
                    eventBackFromSearch = eventHideSearchBar,
                    modifier = Modifier.fillMaxWidth()
                ) { eventGetBooksByName(it) }
            } else {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .clickable {
                            eventShowSearchBar()
                        },
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        }
    )

}
