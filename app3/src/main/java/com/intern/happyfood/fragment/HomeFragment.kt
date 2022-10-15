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
import com.intern.happyfood.model.Restaurant


class HomeFragment : Fragment() {
    lateinit var recyclerHome: RecyclerView

    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerAdapter: HomeRecyclerAdapter

    val resInfoList = arrayListOf<Restaurant>(
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
    ) //Send this list to the Adapter Class

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerHome = view.findViewById(R.id.recyclerHome)
        //For the activity it will be this, and here as activity
        layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = HomeRecyclerAdapter(activity as Context, resInfoList)

        recyclerHome.adapter = recyclerAdapter
        recyclerHome.layoutManager = layoutManager

        return view
    }
}