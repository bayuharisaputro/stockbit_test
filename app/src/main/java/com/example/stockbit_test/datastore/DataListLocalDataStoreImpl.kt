package com.example.stockbit_test.datastore

import android.util.Log
import com.example.stockbit_test.model.Data
import com.example.stockbit_test.util.DBHelper

import javax.inject.Inject
import kotlin.jvm.Throws


class DataListLocalDataStoreImpl @Inject constructor(
    private val localDBHelper: DBHelper
) : DataListLocalDataStore {

    @Throws(Exception::class)
    override suspend fun getDataList(): ArrayList<Data?>? {
        try {
            return localDBHelper.getData()

        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    override suspend fun addDataList(dataList: ArrayList<Data?>?): String? {
        try {
            return localDBHelper.addDataList(dataList)
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    override suspend fun deleteData(): String {
        try {
            return localDBHelper.deleteData()
        } catch (e: java.lang.Exception) {
            Log.e("checkErrpr", e.toString())
            throw e
        }
    }
}