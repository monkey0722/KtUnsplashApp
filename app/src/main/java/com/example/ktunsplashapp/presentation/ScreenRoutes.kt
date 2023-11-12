package com.example.ktunsplashapp.presentation

sealed class ScreenRoutes(val route: String) {
    object SearchPhotosScreen : ScreenRoutes("search_photos_screen")
    object PhotoDetailScreen : ScreenRoutes("photo_detail_screen")
}
