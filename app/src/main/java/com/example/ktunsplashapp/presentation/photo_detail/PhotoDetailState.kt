package com.example.ktunsplashapp.presentation.photo_detail

import com.example.ktunsplashapp.domain.models.PhotoDetail

data class PhotoDetailState(
    val isLoading: Boolean = false,
    val photoDetail: PhotoDetail? = null,
    val error: String? = null,
)
