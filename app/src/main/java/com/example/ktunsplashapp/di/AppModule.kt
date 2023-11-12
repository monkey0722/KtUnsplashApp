package com.example.ktunsplashapp.di

import com.example.ktunsplashapp.BuildConfig
import com.example.ktunsplashapp.data.remote.UnsplashApi
import com.example.ktunsplashapp.data.repositories.PhotoRepositoryImpl
import com.example.ktunsplashapp.domain.repositories.PhotoRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(api: UnsplashApi): PhotoRepository {
        return PhotoRepositoryImpl(api)
    }
}
