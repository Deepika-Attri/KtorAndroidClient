package com.example.ktorandroidclient.data

import com.example.ktorandroidclient.data.models.Article

interface NewsRepository {
    suspend fun getNews(): Resource<List<Article>>
}