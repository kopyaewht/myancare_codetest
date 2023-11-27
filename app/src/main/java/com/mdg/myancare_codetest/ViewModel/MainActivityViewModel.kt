package com.mdg.myancare_codetest.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.BeerDataObj
import com.mdg.myancare_codetest.Model.RequestBeerList
import com.mdg.myancare_codetest.Model.ResponseBeerList
import com.shwephyu.shwephyu.di.NetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _beers = MutableLiveData<List<BeerDataObj>>()
    val beers: LiveData<List<BeerDataObj>> = _beers

    fun GetBeersList(page:Int,per_page:Int) {
        try {
            val userList = networkRepository.BeerList(page,per_page)
            userList.enqueue(object : Callback<List<BeerDataObj>> {
                override fun onResponse(call: Call<List<BeerDataObj>>, response: Response<List<BeerDataObj>>) {
                   // Log.e("onResponsedata==>",response.body()!!.size.toString())
                    if (response.isSuccessful){
                        _beers.postValue(response.body())
                    }

                }

                override fun onFailure(call: Call<List<BeerDataObj>>, t: Throwable) {
                    Log.e("Exception1==>",t.localizedMessage)

                }

            })

        } catch (e: Exception) {
            // Handle error
            Log.e("Exception==>",e.localizedMessage)
        }
    }

}