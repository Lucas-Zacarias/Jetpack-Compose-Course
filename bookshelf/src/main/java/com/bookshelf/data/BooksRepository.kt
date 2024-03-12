package com.bookshelf.data

import com.bookshelf.model.Books
import com.bookshelf.network.BooksApiService
import retrofit2.Response

interface BooksRepository {
    suspend fun getBooks(): Books
    suspend fun getBooksByName(name: String): Books?
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(): Books {
        return booksApiService.getBooks()
    }

    override suspend fun getBooksByName(name: String): Books? {
        return if (booksApiService.getBooksByName(name).isSuccessful)
            booksApiService.getBooksByName(name).body()
        else
            null
    }
}