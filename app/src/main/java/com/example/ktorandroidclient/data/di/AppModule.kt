package com.example.ktorandroidclient.data.di

import com.example.ktorandroidclient.data.NewsRepository
import com.example.ktorandroidclient.data.NewsRepositoryImpl
import com.example.ktorandroidclient.data.network.KtorHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun getHttpClient(httpClient: KtorHttpClient) : HttpClient = httpClient.getHttpClient()

    @Provides
    fun getNewsRepository(impl: NewsRepositoryImpl): NewsRepository = impl
}