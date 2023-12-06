package com.example.ktorandroidclient.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorandroidclient.data.NewsRepository
import com.example.ktorandroidclient.data.Resource
import com.example.ktorandroidclient.data.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableStateFlow<Resource<List<Article>>?>(null)
//    val news = StateFlow<Resource<List<Article>>?> = _news
    val newss = _news.asStateFlow()

    init {
        viewModelScope.launch {
            _news.value = Resource.Loading
            _news.value = repository.getNews()
        }
    }
}