package com.example.capitalone.views

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.ByteString
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

// Define the URLs for your charts
const val BASE_URL = "http://74.208.104.149:5000/"

enum class ChartType(val endpoint: String) {
    CUMULATIVE_SPENDING("chart/cumulative_spending"),
    SPENDING_BY_MERCHANT("chart/spending_by_merchant"),
    PURCHASE_FREQUENCY_BY_MERCHANT("chart/purchase_frequency_by_merchant"),
    CATEGORY_COUNT("chart/category_count"),
    CUMULATIVE_BALANCE("chart/cumulative_balance")
}

// Function to fetch the chart image from the server
suspend fun fetchChartImage(chartType: ChartType): Bitmap? {
    val url = "$BASE_URL${chartType.endpoint}"
    return try {
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            // Verificar si la respuesta es exitosa y el tipo de contenido es imagen
            if (response.isSuccessful && response.body() != null) {
                val contentType = response.header("Content-Type")
                if (contentType != null && contentType.startsWith("image")) {
                    val inputStream: InputStream? = response.body()?.byteStream()
                    BitmapFactory.decodeStream(inputStream)
                } else {
                    null
                }
            } else {
                null
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

// Composable to display the chart image
@Composable
fun ChartImageView(chartType: ChartType) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(chartType) {
        bitmap = fetchChartImage(chartType)
    }

    if (bitmap != null) {
        Image(
            bitmap = bitmap!!.asImageBitmap(),
            contentDescription = "Chart Image",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(
                text = "Loading...",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

// Preview of the ChartImageView with a sample chart type
@Preview(showBackground = true)
@Composable
fun PreviewChartImageView() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ChartImageView(chartType = ChartType.CUMULATIVE_SPENDING)
        }
    }
}
