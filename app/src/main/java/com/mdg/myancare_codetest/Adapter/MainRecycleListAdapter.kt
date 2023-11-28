package com.kintel.shwemyanmar2d3d.RecycleviewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.example.BeerDataObj
import com.mdg.myancare_codetest.R
import com.squareup.picasso.NetworkPolicy

import com.squareup.picasso.Picasso


internal class MainRecycleListAdapter(var context:Context, var itemsList: ArrayList<BeerDataObj>) :
    RecyclerView.Adapter<MainRecycleListAdapter.MyViewHolder>() {

    var onItemClick: ((data: BeerDataObj) -> Unit)? = null



    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       var title: AppCompatTextView = view.findViewById(R.id.title)
        var image: AppCompatImageView = view.findViewById(R.id.image)
        var desc: AppCompatTextView = view.findViewById(R.id.desc)

    }


    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mainlist_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]

        holder.title.text = item.name

        Picasso.get().load(item.imageUrl).into(holder.image)

        holder.desc.text = item.description

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}