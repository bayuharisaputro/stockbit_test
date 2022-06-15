package com.example.stockbit_test.model

import com.google.gson.annotations.SerializedName


data class DataListResponse (

    @SerializedName("Message"       ) var Message       : String?           = null,
    @SerializedName("Type"          ) var Type          : Int?              = null,
    @SerializedName("MetaData"      ) var MetaData      : MetaData?         = MetaData(),
    @SerializedName("SponsoredData" ) var SponsoredData : ArrayList<String> = arrayListOf(),
    @SerializedName("Data"          ) var Data          : ArrayList<Data?>   = arrayListOf(),
    @SerializedName("HasWarning"    ) var HasWarning    : Boolean?          = null

)