package com.example.tasklist.data.remote.client

import com.example.tasklist.data.remote.client.retrofitinterfaces.NewApiRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient(
){

    fun getOkHttpClientBuilder(
        logger: HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    ): OkHttpClient.Builder {
        val client = OkHttpClient.Builder()
        client
//            .log(logger)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS); // Maximum connect timeout
        return client
    }

    fun getNewApiRetrofit(
        okHttpBuilder: OkHttpClient.Builder = getOkHttpClientBuilder()
    ): NewApiRetrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
            .create(
                NewApiRetrofit
                ::class.java)
    }

}