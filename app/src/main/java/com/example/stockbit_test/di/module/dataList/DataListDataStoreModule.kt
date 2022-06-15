package com.example.stockbit_test.di.module.dataList

import com.example.stockbit_test.datastore.DataListLocalDataStore
import com.example.stockbit_test.datastore.DataListLocalDataStoreImpl
import com.example.stockbit_test.datastore.DataListRemoteDataStore
import com.example.stockbit_test.datastore.DataListRemoteDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
interface DataListDataStoreModule {

    @Binds
    fun bindsDataListRemoteDataStore(remoteDataStore: DataListRemoteDataStoreImpl): DataListRemoteDataStore

    @Binds
    fun bindsDataListLocalDataStore(localDataStore: DataListLocalDataStoreImpl): DataListLocalDataStore
}