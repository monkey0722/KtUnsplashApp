package com.example.ktunsplashapp.presentation.search_photos

import com.example.ktunsplashapp.domain.models.Photo

data class SearchPhotosState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String? = null,
)
