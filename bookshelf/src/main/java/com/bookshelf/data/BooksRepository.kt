package com.bookshelf.data

import com.bookshelf.model.Books
import com.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(): Books
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(): Books {
        return booksApiService.getBooks()
    }
}