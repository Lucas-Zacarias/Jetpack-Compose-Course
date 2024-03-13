package com.bookshelf

import com.bookshelf.data.NetworkBooksRepository
import com.bookshelf.fakes.FakeBookshelfApiService
import com.bookshelf.fakes.FakeDataSource
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