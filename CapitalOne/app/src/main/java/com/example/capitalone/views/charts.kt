package com.example.capitalone.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.annotation.WorkerThread
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.loginuser.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

// Define la interfaz Retrofit para tu API
interface ApiService {
    @GET("path/to/your/endpoint")
    suspend fun getChartImage(@Query("param") param: String): ByteArray
}

// Configura Retrofit
fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://localhost:5000/") // Cambia esto a tu URL base
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

// Carga una imagen en un ImageView usando Glide
fun loadImage(url: String, imageView: ImageView) {
    Glide.with(imageView.context)
        .load(url)
        .apply(RequestOptions().placeholder(R.drawable.placeholder_image))
        .into(imageView)
}

// Carga una imagen desde una URL como un Bitmap
@WorkerThread
fun loadBitmapFromUrl(urlString: String): Bitmap? {
    var bitmap: Bitmap? = null
    val url = URL(urlString)
    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
    try {
        connection.inputStream.use { inputStream ->
            bitmap = BitmapFactory.decodeStream(inputStream)
        }
    } finally {
        connection.disconnect()
    }
    return bitmap
}

// Función para obtener la imagen del gráfico desde la API y mostrarla en un ImageView
suspend fun fetchAndDisplayChart(context: Context, imageView: ImageView, param: String) {
    val retrofit = provideRetrofit()
    val apiService = retrofit.create(ApiService::class.java)

    try {
        // Obtener la imagen del gráfico en formato ByteArray
        val imageBytes = apiService.getChartImage(param)
        // Convertir ByteArray a Bitmap
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        // Mostrar la imagen en el ImageView
        imageView.post {
            imageView.setImageBitmap(bitmap)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        // Manejar errores, mostrar imagen de error, etc.
    }
}
