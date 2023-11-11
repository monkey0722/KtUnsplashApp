package com.example.ktunsplashapp.data.remote


import com.example.ktunsplashapp.domain.models.PhotoDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDetailDto(
    @Json(name = "blur_hash")
    val blurHash: String?,
    val color: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "current_user_collections")
    val currentUserCollections: List<CurrentUserCollection>?,
    val description: String?,
    val downloads: Int?,
    val exif: Exif?,
    val height: Int?,
    val id: String?,
    @Json(name = "liked_by_user")
    val likedByUser: Boolean?,
    val likes: Int?,
    val links: LinksXX?,
    val location: Location?,
    @Json(name = "public_domain")
    val publicDomain: Boolean?,
    val tags: List<Tag>?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    val urls: UrlsX?,
    val user: UserX?,
    val width: Int?
)

fun PhotoDetailDto.toPhotoDetail(): PhotoDetail {
    val imageUrl = urls?.raw ?: "https://via.placeholder.com/150" // The fallback image is temporary.
    val locationText = listOfNotNull(location?.city, location?.country).joinToString(", ")
    return PhotoDetail(
        description = description,
        likes = likes,
        imageUrl = imageUrl,
        photographer = user?.username,
        camera = exif?.name,
        location = locationText.ifEmpty { null },
        downloads = downloads
    )
}
