package com.bookshelf.fakes

import com.bookshelf.data.BooksRepository
import com.bookshelf.model.Books

class FakeNetworkBookshelfRepository : BooksRepository {
    override suspend fun getBooks(): Books {
        return FakeDataSource.books
    }

    override suspend fun getBooksByName(name: String): Books? {
        return FakeDataSource.books
    }
}