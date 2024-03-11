package com.bookshelf.fakes

import com.bookshelf.Book
import com.bookshelf.Books
import com.bookshelf.ImageLink
import com.bookshelf.VolumeInfo

object FakeDataSource {
    private val book1 = Book(
        id = "1",
        volumeInfo = VolumeInfo(
            title = "Book1",
            imageLinks = ImageLink(
                thumbnail = "http://image1"
            )
        )
    )

    private val book2 = Book(
        id = "2",
        volumeInfo = VolumeInfo(
            title = "Book2",
            imageLinks = ImageLink(
                thumbnail = "http://image2"
            )
        )
    )

    private val book3 = Book(
        id = "3",
        volumeInfo = VolumeInfo(
            title = "Book3",
            imageLinks = ImageLink(
                thumbnail = "http://image3"
            )
        )
    )

    val books = Books(
        totalItems = 3,
        items = listOf(
            book1,
            book2,
            book3
        )
    )
}