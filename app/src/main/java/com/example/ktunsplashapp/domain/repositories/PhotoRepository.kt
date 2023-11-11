package com.example.ktunsplashapp.domain.repositories

import com.example.ktunsplashapp.data.remote.PhotoDetailDto
import com.example.ktunsplashapp.data.remote.SearchPhotosResultDto

interface PhotoRepository {
    suspend fun searchPhotos(query: String): SearchPhotosResultDto

    suspend fun getPhotoById(photoId: String): PhotoDetailDto
}