package com.example.bookshelf.data

import com.example.bookshelf.model.Books
import com.example.bookshelf.network.BooksApiService

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