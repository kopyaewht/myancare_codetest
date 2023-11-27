package com.example.example

import com.google.gson.annotations.SerializedName


data class Fermentation (

  @SerializedName("temp" ) var temp : Temp? = Temp()

)