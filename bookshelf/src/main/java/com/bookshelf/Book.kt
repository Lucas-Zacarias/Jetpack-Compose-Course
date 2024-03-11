package com.bookshelf

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLink
)

@Serializable
data class ImageLink(
    val thumbnail: String
)
