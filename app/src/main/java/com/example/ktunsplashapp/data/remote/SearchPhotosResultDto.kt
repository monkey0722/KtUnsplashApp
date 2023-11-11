package com.example.ktunsplashapp.data.remote


import com.example.ktunsplashapp.domain.models.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPhotosResultDto(
    val results: List<Result>?,
    val total: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?
)

fun SearchPhotosResultDto.toPhotos(): List<Photo> {
    return results?.mapNotNull { result ->
        if (result.id != null && result.urls?.raw != null) {
            Photo(
                photoId = result.id,
                description = result.description,
                likes = result.likes,
                imageUrl = result.urls.raw,
                photographer = result.user?.username
            )
        } else {
            null
        }
    } ?: emptyList()
}
