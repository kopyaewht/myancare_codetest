package com.mdg.myancare_codetest.Model

import com.google.gson.annotations.SerializedName

data class RequestBeerList(
    @SerializedName("page"     ) var page    : Int? = null,
    @SerializedName("per_page" ) var perPage : Int? = null
)
