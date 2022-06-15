package com.example.stockbit_test.model

import com.google.gson.annotations.SerializedName


data class Weiss (

  @SerializedName("Rating"                   ) var Rating                   : String? = null,
  @SerializedName("TechnologyAdoptionRating" ) var TechnologyAdoptionRating : String? = null,
  @SerializedName("MarketPerformanceRating"  ) var MarketPerformanceRating  : String? = null

)