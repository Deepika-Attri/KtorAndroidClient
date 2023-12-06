package com.example.ktorandroidclient.data

import com.example.ktorandroidclient.data.models.Article
import com.example.ktorandroidclient.data.models.NewsResponse
import com.example.ktorandroidclient.data.network.BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

private const val NEWS_EVERYTHING = "${BASE_URL}/everything"

class NewsRepositoryImpl @Inject constructor(private val httpClient: HttpClient) : NewsRepository {

    override suspend fun getNews(): Resource<List<Article>> {
        return try {
            Resource.Success(httpClient.get<NewsResponse>{
                url(NEWS_EVERYTHING)
            }.articles)
        } catch (e:Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}