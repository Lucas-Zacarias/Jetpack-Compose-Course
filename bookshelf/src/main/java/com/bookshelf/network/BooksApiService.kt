package com.bookshelf.network

import com.bookshelf.model.Books
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes?q=jazz+history")
    suspend fun getBooks(): Books

    @GET("volumes?")
    suspend fun getBooksByName(@Query("q") name: String): Response<Books>
}