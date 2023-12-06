package com.example.ktorandroidclient.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.ktorandroidclient.data.Resource
import com.example.ktorandroidclient.ui.news.NewsList
import com.example.ktorandroidclient.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val news = viewModel.newss.collectAsState()

            AppTheme {
                news.value?.let {
                    when (it) {
                        is Resource.Failure -> {
                            Toast.makeText(context, it.exception.message!!, Toast.LENGTH_SHORT).show()
                        }

                        Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            NewsList(it.result)
                        }
                    }
                }
            }
        }
    }
}