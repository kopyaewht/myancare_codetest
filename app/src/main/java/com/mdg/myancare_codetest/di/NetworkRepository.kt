package com.shwephyu.shwephyu.di


import com.example.example.BeerDataObj
import com.mdg.myancare_codetest.Model.RequestBeerList
import com.mdg.myancare_codetest.Model.ResponseBeerList
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val service: NetworkService) {

     fun BeerList(page:Int,per_page:Int): Call<List<BeerDataObj>> {
        return service.BeerList(page,per_page)
    }





}