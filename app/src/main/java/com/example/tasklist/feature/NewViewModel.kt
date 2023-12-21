package com.example.tasklist.feature

import androidx.lifecycle.ViewModel
import com.example.tasklist.data.remote.api.NewsApiImpl
import com.example.tasklist.data.remote.client.RetrofitClient
import com.example.tasklist.data.remote.model.response.Articles
import com.example.tasklist.domain.usecase.GetNewsUseCase

class NewViewModel : ViewModel() {
    val newsUseCase: GetNewsUseCase =
        GetNewsUseCase(NewsApiImpl(RetrofitClient().getNewApiRetrofit()))
    val newsData = newsUseCase.currentData
    fun getNews() = newsUseCase.setup(null)

    var currentSelectedNew : Articles = Articles()
}