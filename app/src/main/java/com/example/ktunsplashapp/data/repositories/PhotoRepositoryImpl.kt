package com.example.ktunsplashapp.data.repositories

import com.example.ktunsplashapp.data.remote.PhotoDetailDto
import com.example.ktunsplashapp.data.remote.SearchPhotosResultDto
import com.example.ktunsplashapp.data.remote.UnsplashApi
import com.example.ktunsplashapp.domain.repositories.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
) : PhotoRepository {

    override suspend fun searchPhotos(query: String): SearchPhotosResultDto {
        return api.searchPhotos(query)
    }

    override suspend fun getPhotoById(photoId: String): PhotoDetailDto {
        return api.getPhotoById(photoId)
    }
}