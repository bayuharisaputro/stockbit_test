package com.example.stockbit_test.repository

import com.example.stockbit_test.datastore.DataListLocalDataStore
import com.example.stockbit_test.datastore.DataListRemoteDataStore
import com.example.stockbit_test.exception.NoInternetException
import com.example.stockbit_test.model.DataListResponse
import com.example.stockbit_test.util.ConnectionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.Throws

class DataListRepositoryImpl @Inject constructor(
    private val remoteDataStore: DataListRemoteDataStore,
    private val localDataStore: DataListLocalDataStore,
    private val networkUtil: ConnectionUtil
) : DataListRepository {
    @Throws(Exception::class)
    override suspend fun getDataList(page: Int,tsym: String, isRefresh: Boolean, isLoadMore: Boolean): DataListResponse? = withContext(Dispatchers.IO){
        if(isLoadMore) {
            if (networkUtil.isInternetConnected()) {
                val data = remoteDataStore.getDataList(page, tsym)
                localDataStore.addDataList(data?.Data)
                return@withContext data
            } else {
                throw NoInternetException()
            }
        } else {
            if(isRefresh) {
                localDataStore.deleteData()
            }
            var finalData:DataListResponse? = DataListResponse()
            val localData = localDataStore.getDataList()?: arrayListOf()
            if(localData.isEmpty()) {
                finalData = remoteDataStore.getDataList(page, tsym)
                localDataStore.addDataList(finalData?.Data)
            } else {
                finalData = DataListResponse(Data = localData)
            }
            return@withContext finalData
        }
    }
}