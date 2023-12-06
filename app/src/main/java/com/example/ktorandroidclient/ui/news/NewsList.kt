package com.example.ktorandroidclient.ui.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ktorandroidclient.data.models.Article

@Composable
fun NewsList(newsArticle: List<Article>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(newsArticle) { item ->
                NewsItem(item)
            }
        }
    }
}