package com.example.bookshelf.network

import com.example.bookshelf.model.Books
import retrofit2.http.GET

interface BooksApiService {
    @GET("volumes?q=jazz+history")
    suspend fun getBooks(): Books
}