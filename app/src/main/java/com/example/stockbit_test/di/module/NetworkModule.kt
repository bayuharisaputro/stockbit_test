package com.example.stockbit_test.di.module

import com.example.stockbit_test.api.ApiClient
import com.example.stockbit_test.api.ApiInterface
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {
    @Provides
    @JvmStatic
    fun provideApiInterfaceSync(): ApiInterface {
        return ApiClient.client.create(ApiInterface::class.java)
    }
}