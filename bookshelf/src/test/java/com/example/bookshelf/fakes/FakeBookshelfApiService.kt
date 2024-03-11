package com.example.bookshelf.fakes

import com.example.bookshelf.model.Books
import com.example.bookshelf.network.BooksApiService

class FakeBookshelfApiService : BooksApiService {
    override suspend fun getBooks(): Books {
        return FakeDataSource.books
    }
}