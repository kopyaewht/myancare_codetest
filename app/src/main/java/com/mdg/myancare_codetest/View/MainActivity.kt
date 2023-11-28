package com.mdg.myancare_codetest.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.BeerDataObj
import com.kintel.shwemyanmar2d3d.RecycleviewAdapter.MainRecycleListAdapter
import com.mdg.myancare_codetest.Model.RequestBeerList
import com.mdg.myancare_codetest.R
import com.mdg.myancare_codetest.Utli.NetworkChecker
import com.mdg.myancare_codetest.Utli.SharedPreferencesHelper
import com.mdg.myancare_codetest.ViewModel.MainActivityViewModel
import com.mdg.myancare_codetest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    @Inject
    lateinit var viewModel: MainActivityViewModel

    private lateinit var mainRecycleListAdapter: MainRecycleListAdapter

    var datalist = ArrayList<BeerDataObj>()

    var pagenumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainRecycleListAdapter = MainRecycleListAdapter(this,datalist)
       var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recycleview.layoutManager = layoutManager
        binding.recycleview.adapter = mainRecycleListAdapter

        //ViewModel Listener Observation
        ViewModelListener()

       //Sync
        SyncAPI()

       // Pagination Scrolling
        binding.recycleview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager
                val visibleItemCount = layoutManager?.childCount ?: 0
                val totalItemCount = layoutManager?.itemCount ?: 0
                val firstVisibleItemPosition =
                    (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: 0

                val isAtBottom = firstVisibleItemPosition + visibleItemCount >= totalItemCount
                if (isAtBottom && totalItemCount > 0) {

                    if (datalist.size > 9){
                        if (NetworkChecker().isInternetConnected(applicationContext)){
                            pagenumber += 1
                            viewModel.GetBeersList(pagenumber,10)
                        }else{
                            Toast.makeText(applicationContext,"Please connect Internet",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })



    }

    private fun SyncAPI() {

        if (NetworkChecker().isInternetConnected(this)){
            binding.loading.visibility = View.VISIBLE
            viewModel.GetBeersList(pagenumber,10)
        }else{
            var storedata = SharedPreferencesHelper(this).getArrayList("data")
            if (storedata != null){
                if (storedata.size > 0){
                    datalist.addAll(storedata)
                    mainRecycleListAdapter.notifyDataSetChanged()
                }
            }
            Toast.makeText(this,"Please connect Internet",Toast.LENGTH_SHORT).show()
        }


    }

    private fun ViewModelListener() {

        viewModel.beers.observe(this) {
            binding.loading.visibility = View.GONE
            datalist.addAll(it)
            if (datalist.size > 0) {
                SharedPreferencesHelper(this).ClearData("data")
                SharedPreferencesHelper(this).saveArrayList("data", datalist)
            }
            mainRecycleListAdapter.notifyDataSetChanged()

        }


    }
}