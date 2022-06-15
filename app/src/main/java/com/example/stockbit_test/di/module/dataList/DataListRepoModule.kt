package com.example.stockbit_test.di.module.dataList

import com.example.stockbit_test.repository.DataListRepository
import com.example.stockbit_test.repository.DataListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataListRepoModule {

    @Binds
    fun bindsDataListRepository(repo: DataListRepositoryImpl): DataListRepository
}