package com.bookshelf.fakes

import com.bookshelf.model.Books
import com.bookshelf.network.BooksApiService
import retrofit2.Response

class FakeBookshelfApiService : BooksApiService {
    override suspend fun getBooks(): Books {
        return FakeDataSource.books
    }

    override suspend fun getBooksByName(name: String): Response<Books> {
        return Response.success(FakeDataSource.books)
    }
}