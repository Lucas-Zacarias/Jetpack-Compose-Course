package com.example.bookshelf

import com.example.bookshelf.fakes.FakeDataSource
import com.example.bookshelf.fakes.FakeNetworkBookshelfRepository
import com.example.bookshelf.ui.BookshelfUiState
import com.example.bookshelf.ui.BookshelfViewModel
import com.marsphotos.rules.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()
    @Test
    fun bookshelfViewModel_getBooksImages_verifyBooksImagesList() = runTest {
        val viewModel = BookshelfViewModel(
            booksRepository = FakeNetworkBookshelfRepository()
        )
        assertEquals(
            BookshelfUiState.Success(FakeDataSource.books.booksToHttpsList()),
            viewModel.uiState
        )
    }
}