package com.example.bookshelf

import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.fakes.FakeBookshelfApiService
import com.example.bookshelf.fakes.FakeDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkBookshelfRepositoryTest {
    @Test
    fun networkBookshelfRepository_getBooksImages_verifyImageList() = runTest {
        val repository = NetworkBooksRepository(
            booksApiService = FakeBookshelfApiService()
        )
        assertEquals(FakeDataSource.books, repository.getBooks())
    }
}