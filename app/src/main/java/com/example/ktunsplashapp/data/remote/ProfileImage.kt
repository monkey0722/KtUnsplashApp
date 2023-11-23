package com.example.ktunsplashapp.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileImage(
    val large: String?,
    val medium: String?,
    val small: String?,
)
