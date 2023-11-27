package com.shwephyu.shwephyu.di


import com.example.example.BeerDataObj
import com.mdg.myancare_codetest.Model.RequestBeerList
import com.mdg.myancare_codetest.Model.ResponseBeerList
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface NetworkService {

     @GET("beers")
     fun BeerList(@Query("page") page: Int, @Query("per_page") per_page: Int): Call<List<BeerDataObj>>



}