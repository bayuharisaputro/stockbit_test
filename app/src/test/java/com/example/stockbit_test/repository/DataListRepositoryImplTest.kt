package com.example.stockbit_test.repository

import com.example.stockbit_test.datastore.DataListLocalDataStoreImpl
import com.example.stockbit_test.datastore.DataListRemoteDataStoreImpl
import com.example.stockbit_test.exception.NoInternetException
import com.example.stockbit_test.model.Data
import com.example.stockbit_test.model.DataListResponse
import com.example.stockbit_test.util.ConnectionUtil
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DataListRepositoryImplTest {
    @Mock
    var remoteDataStoreImpl : DataListRemoteDataStoreImpl? = null

    @Mock
    var localDataStoreImpl : DataListLocalDataStoreImpl? = null
    @Mock
    var netUtil : ConnectionUtil? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getData_normal() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getDataList(1,"USD")).thenReturn(DataListResponse())
            Mockito.`when`(localDataStoreImpl?.getDataList()).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(true)
            val getDataRepository : DataListRepositoryImpl = DataListRepositoryImpl(remoteDataStoreImpl!!,localDataStoreImpl!!, netUtil!!)

            val res = getDataRepository?.getDataList(1, "USD", true, true)
            Mockito.verify(remoteDataStoreImpl, Mockito.times(1))?.getDataList(1, "USD")
            Mockito.verify(localDataStoreImpl, Mockito.times(1))?.addDataList(arrayListOf())
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getData_normal_local() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getDataList(1,"USD")).thenReturn(DataListResponse())
            Mockito.`when`(localDataStoreImpl?.getDataList()).thenReturn(arrayListOf(Data(),Data()))
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(true)
            val getDataRepository : DataListRepositoryImpl = DataListRepositoryImpl(remoteDataStoreImpl!!,localDataStoreImpl!!, netUtil!!)

            val res = getDataRepository?.getDataList(1, "USD", false, false)
            Mockito.verify(remoteDataStoreImpl, Mockito.never())?.getDataList(1, "USD")
            Mockito.verify(localDataStoreImpl, Mockito.times(1))?.getDataList()
            Truth.assertThat(res).isNotNull()
        }
    }


    @Test
    fun getData_error() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getDataList(1,"USD")).thenReturn(DataListResponse())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(false)
            val getDataRepository : DataListRepositoryImpl = DataListRepositoryImpl(remoteDataStoreImpl!!,localDataStoreImpl!!, netUtil!!)

            try {
                val res = getDataRepository?.getDataList(1, "USD", true, true)
                throw RuntimeException("Is must not be here")
            }
            catch (e : NoInternetException) {
                //expected to throw No Internet Exception
            }
        }
    }
}