package com.example.stockbit_test.datastore

import com.example.stockbit_test.model.DataListResponse


interface DataListRemoteDataStore {
    suspend fun getDataList(page: Int,tsym: String): DataListResponse?
}