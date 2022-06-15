package com.example.stockbit_test.model

import com.google.gson.annotations.SerializedName


data class Data (

    @SerializedName("CoinInfo" ) var CoinInfo : CoinInfo? = CoinInfo(),
    @SerializedName("RAW"      ) var RAW      : RAW?      = RAW(),
    @SerializedName("DISPLAY"  ) var DISPLAY  : DISPLAY?  = DISPLAY()

)