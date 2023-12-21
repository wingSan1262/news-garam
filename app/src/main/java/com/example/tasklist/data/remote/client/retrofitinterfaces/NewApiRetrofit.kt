package com.example.tasklist.data.remote.client.retrofitinterfaces

import com.example.tasklist.data.remote.model.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApiRetrofit {
    @GET("v2/top-headlines")
    suspend fun getTemperatureChart(
        @Query("country") country : String = "us",
        @Query("apiKey") apiKey : String = "bd3e283759ad4ac5a2adefbf32356380",
    ): NewsResponse

}