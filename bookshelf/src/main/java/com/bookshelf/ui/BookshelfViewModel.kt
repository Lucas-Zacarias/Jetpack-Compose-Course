package com.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bookshelf.BooksApplication
import com.bookshelf.data.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val booksUrl: List<String>) : BookshelfUiState
    data object Loading : BookshelfUiState
    data object Error: BookshelfUiState

    data object TitleNotFound: BookshelfUiState
}

data class BookSearchUiState(
    val isSearchBarVisible: Boolean = false
)

class BookshelfViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {
    var uiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
    private val _bookSearchUiState = MutableStateFlow(BookSearchUiState())
    val bookSearchUiState: StateFlow<BookSearchUiState> = _bookSearchUiState.asStateFlow()
    var currentSearch by mutableStateOf("")
        private set
    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            if(uiState is BookshelfUiState.Error) {
                uiState = BookshelfUiState.Loading
            }
            uiState = try {
                BookshelfUiState.Success(booksRepository.getBooks().booksToHttpsList())
            } catch (e: IOException) {
                BookshelfUiState.Error
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun getBooksByName(name: String) {
        viewModelScope.launch {
            currentSearch = name
            uiState = try {
                BookshelfUiState.Success(booksRepository.getBooksByName(name)?.booksToHttpsList() ?: emptyList())
            } catch (e: MissingFieldException) {
                BookshelfUiState.TitleNotFound
            }
        }
    }

    fun resetUiStateFromStateTitleNotFound() {
        viewModelScope.launch {
            if(uiState is BookshelfUiState.TitleNotFound) {
                getBooks()
            }
            resetRecentSearch()
        }
    }

    fun openSearchBar() {
        _bookSearchUiState.update {
            it.copy(
                isSearchBarVisible = true
            )
        }
    }

    fun closeSearchBar() {
        _bookSearchUiState.update {
            it.copy(
                isSearchBarVisible = false
            )
        }
        currentSearch = ""
    }

    private fun resetRecentSearch() {
        currentSearch = ""
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BooksApplication)
                val booksRepository = application.container.booksRepository
                BookshelfViewModel(booksRepository = booksRepository)
            }
        }
    }
}