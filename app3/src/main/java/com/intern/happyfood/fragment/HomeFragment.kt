package com.intern.happyfood.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intern.happyfood.R
import com.intern.happyfood.adapter.HomeRecyclerAdapter


class HomeFragment : Fragment() {
    lateinit var recyclerHome: RecyclerView

    lateinit var layoutManager: RecyclerView.LayoutManager

    val restaurantList = arrayListOf(
        "Pizza Hut",
        "Dominos",
        "Mirch Masala",
        "Zayka",
        "Kalika",
        "KFC",
        "Reliance",
        "Nokia",
        "Amazon",
        "Respy",
        "Zomato",
        "Online Kaka"
    )

    lateinit var recyclerAdapter: HomeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerHome = view.findViewById(R.id.recyclerHome)
        //For the activity it will be this, and here as activity
        layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = HomeRecyclerAdapter(activity as Context, restaurantList)

        recyclerHome.adapter = recyclerAdapter
        recyclerHome.layoutManager = layoutManager

        return view
    }
}