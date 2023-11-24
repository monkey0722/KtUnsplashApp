package com.example.ktunsplashapp.domain.use_cases

import com.example.ktunsplashapp.BuildConfig
import com.example.ktunsplashapp.data.remote.toPhotos
import com.example.ktunsplashapp.domain.models.Photo
import com.example.ktunsplashapp.domain.repositories.PhotoRepository
import com.example.ktunsplashapp.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPhotosUseCase
    @Inject
    constructor(
        private val repository: PhotoRepository,
    ) {
        operator fun invoke(query: String): Flow<NetworkResponse<List<Photo>>> =
            flow {
                try {
                    emit(NetworkResponse.Loading<List<Photo>>())
                    val photos = repository.searchPhotos(query).toPhotos()
                    emit(NetworkResponse.Success<List<Photo>>(photos))
                } catch (e: Exception) {
                    if (BuildConfig.DEBUG) {
                        e.printStackTrace()
                    }
                    emit(NetworkResponse.Failure<List<Photo>>(e.message.toString()))
                }
            }
    }
