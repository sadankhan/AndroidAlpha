package com.intern.happyfood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intern.happyfood.R
import com.intern.happyfood.model.Restaurant

class HomeRecyclerAdapter(val context: Context, private val itemList: ArrayList<Restaurant>):
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_dashboard_single_row, parent, false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val restaurant = itemList[position]
        holder.textResName.text = restaurant.resName
        holder.textResRating.text = restaurant.resRating
        holder.textAvgCost.text = restaurant.avgCost
        holder.imgResImage.setBackgroundResource(restaurant.resImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textResName: TextView = view.findViewById(R.id.txtResName)
        val textResRating: TextView = view.findViewById(R.id.txtResRating)
        val textAvgCost: TextView = view.findViewById(R.id.txtCostForOne)
        val imgResImage: ImageView = view.findViewById(R.id.imgResImage)
    }
}