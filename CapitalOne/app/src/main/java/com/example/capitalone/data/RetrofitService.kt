package com.example.loginuser.data

import com.example.capitalone.data.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// http://api.nessieisreal.com/accounts/66e5f7339683f20dd5189bbd?key=29d59fede15d5425ba792677fa6b82ba

interface RetrofitService {
    @GET("/accounts/66e5f7339683f20dd5189bbd?key=29d59fede15d5425ba792677fa6b82ba")
    suspend fun getApiInfo(
        //@Query("key") apiKey: String,
    ): RemoteResult
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("http://api.nessieisreal.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}