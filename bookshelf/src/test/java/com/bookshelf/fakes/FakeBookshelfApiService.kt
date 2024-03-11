package com.bookshelf.fakes

import com.bookshelf.Books
import com.bookshelf.network.BooksApiService

class FakeBookshelfApiService : BooksApiService {
    override suspend fun getBooks(): Books {
        return FakeDataSource.books
    }
}