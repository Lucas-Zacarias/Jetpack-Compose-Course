package com.bookshelf.network

import com.bookshelf.model.Books
import retrofit2.http.GET

interface BooksApiService {
    @GET("volumes?q=jazz+history")
    suspend fun getBooks(): Books
}