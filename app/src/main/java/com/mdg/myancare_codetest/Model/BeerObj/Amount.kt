package com.example.example

import com.google.gson.annotations.SerializedName


data class Amount (

  @SerializedName("value" ) var value : Int?    = null,
  @SerializedName("unit"  ) var unit  : String? = null

)