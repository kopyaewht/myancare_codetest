package com.mdg.myancare_codetest.Model

import com.google.gson.annotations.SerializedName

data class ResponseBeerList(
    @SerializedName("page"     ) var page    : Int? = null,
    )
