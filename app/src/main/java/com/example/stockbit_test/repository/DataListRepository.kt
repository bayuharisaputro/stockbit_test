package com.example.stockbit_test.repository

import com.example.stockbit_test.model.DataListResponse

interface DataListRepository {
    suspend fun getDataList(page: Int,tsym: String, isRefresh: Boolean,
                            isLoadMore: Boolean): DataListResponse?
}