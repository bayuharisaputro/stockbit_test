package com.example.stockbit_test.api

import com.example.stockbit_test.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private var retrofit: Retrofit? = null

    const val base_url = "https://min-api.cryptocompare.com/"

    val client: Retrofit
        get() {
            if (retrofit == null) {
                val httpClient = OkHttpClient().newBuilder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(90, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)

                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                    httpClient.addInterceptor(interceptor)
                }

                val okHttpClient = httpClient.build()
                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
}