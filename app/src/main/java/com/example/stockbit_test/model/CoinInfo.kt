package com.example.stockbit_test.model

import com.google.gson.annotations.SerializedName


data class CoinInfo (

  @SerializedName("Id"                 ) var Id                 : String? = null,
  @SerializedName("Name"               ) var Name               : String? = null,
  @SerializedName("FullName"           ) var FullName           : String? = null,
  @SerializedName("Internal"           ) var Internal           : String? = null,
  @SerializedName("ImageUrl"           ) var ImageUrl           : String? = null,
  @SerializedName("Url"                ) var Url                : String? = null,
  @SerializedName("Algorithm"          ) var Algorithm          : String? = null,
  @SerializedName("ProofType"          ) var ProofType          : String? = null,
  @SerializedName("Rating"             ) var Rating             : Rating? = Rating(),
  @SerializedName("NetHashesPerSecond" ) var NetHashesPerSecond : Double?    = null,
  @SerializedName("BlockNumber"        ) var BlockNumber        : Double?    = null,
  @SerializedName("BlockTime"          ) var BlockTime          : Double?    = null,
  @SerializedName("BlockReward"        ) var BlockReward        : Double? = null,
  @SerializedName("AssetLaunchDate"    ) var AssetLaunchDate    : String? = null,
  @SerializedName("MaxSupply"          ) var MaxSupply          : Double? = null,
  @SerializedName("Type"               ) var Type               : Int?    = null,
  @SerializedName("DocumentType"       ) var DocumentType       : String? = null

)