package com.example.ktunsplashapp.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksX(
    val html: String?,
    val likes: String?,
    val photos: String?,
    val self: String?,
)
