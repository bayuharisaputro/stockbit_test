package com.example.stockbit_test.api

import com.example.stockbit_test.model.DataListResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("data/top/totaltoptiervolfull")
    fun getDataList(@Query("page") page: Int,
                    @Query("tsym") tsym: String,
                    @Query("limit") limit: Int): Call<DataListResponse>
}