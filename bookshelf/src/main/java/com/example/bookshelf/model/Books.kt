package com.example.bookshelf.model

import com.example.bookshelf.data.util.toHttps
import kotlinx.serialization.Serializable

@Serializable
data class Books(
    val totalItems: Int,
    val items: List<Book>
) {
    fun booksToHttpsList(): List<String> {
        val listUrl = mutableListOf<String>()
        items.forEach {
            listUrl.add(it.volumeInfo.imageLinks.thumbnail.toHttps())
        }
        return listUrl
    }
}
