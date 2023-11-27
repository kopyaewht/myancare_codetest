package com.mdg.myancare_codetest.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mdg.myancare_codetest.Model.RequestBeerList
import com.mdg.myancare_codetest.R
import com.mdg.myancare_codetest.ViewModel.MainActivityViewModel
import com.mdg.myancare_codetest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.beers.observe(this,{

            Log.e("beerlist==>",it.size.toString())

        })

        viewModel.GetBeersList(1,10)

    }
}