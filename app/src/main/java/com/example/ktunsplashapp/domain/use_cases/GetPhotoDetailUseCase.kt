package com.example.ktunsplashapp.domain.use_cases

import com.example.ktunsplashapp.BuildConfig
import com.example.ktunsplashapp.data.remote.toPhotoDetail
import com.example.ktunsplashapp.domain.models.PhotoDetail
import com.example.ktunsplashapp.domain.repositories.PhotoRepository
import com.example.ktunsplashapp.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(
    private val repository: PhotoRepository,
) {
    operator fun invoke(photoId: String): Flow<NetworkResponse<PhotoDetail>> = flow {
        try {
            emit(NetworkResponse.Loading<PhotoDetail>())
            val photoDetail = repository.getPhotoById(photoId).toPhotoDetail()
            emit(NetworkResponse.Success<PhotoDetail>(photoDetail))
        } catch(e: Exception) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
            emit(NetworkResponse.Failure<PhotoDetail>(e.message.toString()))
        }
    }
}