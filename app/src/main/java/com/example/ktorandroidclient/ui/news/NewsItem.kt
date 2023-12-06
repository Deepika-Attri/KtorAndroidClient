package com.example.ktorandroidclient.ui.news

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ktorandroidclient.R
import com.example.ktorandroidclient.data.models.Article
import com.example.ktorandroidclient.ui.NewsScreenActivity
import com.example.ktorandroidclient.ui.theme.AppTheme
import com.example.ktorandroidclient.ui.theme.spacing

@Composable
fun NewsItem(newsArticle: Article) {
    val spacing = MaterialTheme.spacing
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.surface
                    ),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(spacing.extraSmall)
            .clip(RoundedCornerShape(spacing.small))
            .shadow(elevation = 1.dp)
            .clickable {
                val intent = Intent(context, NewsScreenActivity::class.java)
                intent.putExtra("newsArticle", newsArticle)
                context.startActivity(intent)
            },
        contentAlignment = Alignment.Center,
    ) {

        Row(
            modifier = Modifier
                .padding(spacing.small)
                .fillMaxWidth()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(newsArticle.urlToImage)
                    .crossfade(true).error(R.drawable.bg_image_placeholder).build(),
                placeholder = painterResource(R.drawable.bg_image_placeholder),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(0.4f)
            )

            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = spacing.medium)
            ) {
                Text(
                    text = newsArticle.title.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.size(spacing.medium))

                Text(
                    text = newsArticle.description.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
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
        NewsItem(newsArticle = article)
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
        NewsItem(newsArticle = article)
    }
}
