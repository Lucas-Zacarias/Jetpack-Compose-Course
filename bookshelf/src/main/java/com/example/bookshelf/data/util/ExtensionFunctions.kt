package com.example.bookshelf.data.util

fun String.toHttps(): String {
    return this.replace("http", "https")
}