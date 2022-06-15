package com.example.stockbit_test.datastore

import com.example.stockbit_test.util.DBHelper
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DataListLocalDataStoreImplTest {
    @Mock
    var dbHelper : DBHelper? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getDataList() {
        runBlocking {
            val localStore = DataListLocalDataStoreImpl(dbHelper!!)
            localStore.getDataList()
            Mockito.verify(dbHelper, Mockito.times(1))?.getData()
        }
    }

    @Test
    fun addDataList() {
        runBlocking {
            val localStore = DataListLocalDataStoreImpl(dbHelper!!)
            localStore.addDataList(arrayListOf())
            Mockito.verify(dbHelper, Mockito.times(1))?.addDataList(arrayListOf())
        }
    }

    @Test
    fun deleteData() {
        runBlocking {
            val localStore = DataListLocalDataStoreImpl(dbHelper!!)
            localStore.deleteData()
            Mockito.verify(dbHelper, Mockito.times(1))?.deleteData()
        }
    }
}