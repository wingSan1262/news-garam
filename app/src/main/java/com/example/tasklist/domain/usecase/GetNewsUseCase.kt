package com.example.tasklist.domain.usecase

import com.example.tasklist.base.base_classes.BaseUseCase
import com.example.tasklist.data.remote.api.NewsApi
import com.example.tasklist.data.remote.client.retrofitinterfaces.NewApiRetrofit
import com.example.tasklist.data.remote.model.response.NewsResponse


class GetNewsUseCase(
    val teaApi: NewsApi
) : BaseUseCase<Any?, NewsResponse>() {
    override fun setup(parameter: Any?) {
        super.setup(parameter)
        execute {
            return@execute teaApi.getWeather()
        }
    }
}

