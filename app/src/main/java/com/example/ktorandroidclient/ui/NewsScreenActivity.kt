package com.example.ktorandroidclient.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import com.example.ktorandroidclient.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ktorandroidclient.data.models.Article
import com.example.ktorandroidclient.ui.theme.AppTheme
import com.example.ktorandroidclient.ui.theme.spacing
import com.example.ktorandroidclient.utils.getSerializableData

class NewsScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            val newsArticle = it.getSerializableData("newsArticle", Article::class.java)
            setContent {
                newsArticle?.let { it1 -> NewsItem(it1) }
            }
        }
    }
}

@Composable
fun NewsItem(newsArticle: Article) {
    val context = LocalContext.current
    val spacing = MaterialTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(spacing.medium)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
            contentDescription = stringResource(id = R.string.app_name),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    (context as NewsScreenActivity).finish()
                },
        )
        Spacer(modifier = Modifier.size(spacing.medium))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(newsArticle.urlToImage)
                .error(R.drawable.bg_image_placeholder)
                .crossfade(true).build(),
            placeholder = painterResource(id = R.drawable.bg_image_placeholder),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(350.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(spacing.medium))
        Text(
            text = newsArticle.title.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.size(spacing.medium))
        Text(
            text = newsArticle.description.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NewsItemPreviewLight() {
    val article = Article(
        content = "Sample content",
        description = "Sample description",
        publishedAt = "2023-12-06", // Sample date
        title = "Sample title",
        url = "https://sampleurl.com",
        urlToImage = "https://sampleimageurl.com/image.jpg"
    )

    AppTheme {
        NewsItem(article)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewsItemPreviewDark() {
    val article = Article(
        content = "Sample content",
        description = "Sample description",
        publishedAt = "2023-12-06", // Sample date
        title = "Sample title",
        url = "https://sampleurl.com",
        urlToImage = "https://sampleimageurl.com/image.jpg"
    )

    AppTheme {
        NewsItem(article)
    }
}