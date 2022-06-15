package com.example.stockbit_test.datastore

import com.example.stockbit_test.model.Data


interface DataListLocalDataStore {
    suspend fun getDataList(): ArrayList<Data?>?

    suspend fun addDataList(dataList: ArrayList<Data?>?): String?
    suspend fun deleteData(): String?

}