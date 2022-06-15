package com.example.stockbit_test.datastore
import android.util.Log
import com.example.stockbit_test.api.ApiInterface
import com.example.stockbit_test.exception.ApiResponseException
import com.example.stockbit_test.model.DataListResponse

import javax.inject.Inject
import kotlin.jvm.Throws


class DataListRemoteDataStoreImpl @Inject constructor(
        private val api: ApiInterface
) : DataListRemoteDataStore {

    @Throws(Exception::class)
    override suspend fun getDataList(page: Int, tsym: String): DataListResponse? {
        try {
            val apiRes = api.getDataList(page, tsym, 50).execute()
            try {
                if (apiRes.isSuccessful) {
                    return apiRes.body()
                } else {
                    throw ApiResponseException(apiRes.code().toString(), apiRes.message())
                }
            } catch (e: Exception) {
                throw e
            }
        } catch (e: java.lang.Exception) {
            throw e
        }
    }
}