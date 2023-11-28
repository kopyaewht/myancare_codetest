package com.example.example

import android.provider.ContactsContract.Data
import com.google.gson.annotations.SerializedName


data class BeerDataObj (

  @SerializedName("id"                ) var id               : Int?              = null,
  @SerializedName("name"              ) var name             : String?           = null,
  @SerializedName("tagline"           ) var tagline          : String?           = null,
  @SerializedName("first_brewed"      ) var firstBrewed      : String?           = null,
  @SerializedName("description"       ) var description      : String?           = null,
  @SerializedName("image_url"         ) var imageUrl         : String?           = null,

)