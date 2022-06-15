package com.example.stockbit_test.datastore

import com.example.stockbit_test.api.ApiInterface
import com.example.stockbit_test.model.DataListResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class DataListRemoteDataStoreImplTest {
    @Mock
    val apiInterface : ApiInterface? = null

    @Mock
    val response : Response<DataListResponse>?= null

    @Mock
    val callRetrofit : Call<DataListResponse>? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(apiInterface?.getDataList(1,"USD", 50)).thenReturn(callRetrofit)
    }
    @Test
    fun getDataList_normal() {
        runBlocking {
            Mockito.`when`(callRetrofit?.execute()).thenReturn(response)
            Mockito.`when`(response?.body()).thenReturn(DataListResponse())
            Mockito.`when`(response?.isSuccessful).thenReturn(true)
            val remoteGetData = DataListRemoteDataStoreImpl(apiInterface!!)
            val res = remoteGetData.getDataList(1,"USD")
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getComment_error() {
        runBlocking {
            Mockito.`when`(callRetrofit?.execute()).thenThrow(IOException("Error"))
            val remoteGetData = DataListRemoteDataStoreImpl(apiInterface!!)
            try{
                remoteGetData.getDataList(1,"USD")
                throw RuntimeException("Is must not be here")
            }
            catch (e : Exception){
                Truth.assertThat(e.message).isEqualTo("Error")
            }
        }
    }
}