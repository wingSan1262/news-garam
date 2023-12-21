package com.example.tasklist.data.remote.api

import com.example.tasklist.data.remote.client.retrofitinterfaces.NewApiRetrofit
import com.example.tasklist.data.remote.model.response.NewsResponse
interface NewsApi {
    suspend fun getWeather() : NewsResponse
}

class NewsApiImpl(
    val teaRetrofitInterface: NewApiRetrofit
) : NewsApi {
    override suspend fun getWeather(): NewsResponse {
        return teaRetrofitInterface.getTemperatureChart()
    }
}