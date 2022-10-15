package com.intern.happyfood.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intern.happyfood.R
import com.intern.happyfood.adapter.HomeRecyclerAdapter
import com.intern.happyfood.model.Restaurant
import com.intern.happyfood.util.ConnectionManager


class HomeFragment : Fragment() {
    private lateinit var recyclerHome: RecyclerView

    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var recyclerAdapter: HomeRecyclerAdapter

    private lateinit var btnCheckInternet: Button

    private val resInfoList = arrayListOf(
        Restaurant("KFC", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("Pizza Hut", "5.0", "300/person", R.drawable.ic_launcher_background),
        Restaurant("McDonald", "5.0", "300/person", R.drawable.ic_launcher_background)
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

        btnCheckInternet = view.findViewById(R.id.btnCheckInternet)
        btnCheckInternet.setOnClickListener {
            if(ConnectionManager().checkConnectivity(activity as Context)) {
                //Internet is Available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success!")
                dialog.setMessage("Internet Connection Found!")
                dialog.setPositiveButton("OK"){
                    text, listener ->
                    //Do Nothing
                }
                dialog.setNegativeButton("Cancel") {
                    text, listener ->
                }
                dialog.create()
                dialog.show()
            } else {
                //Internet is Unavailable
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error!")
                dialog.setMessage("No Internet Connection!")
                dialog.setPositiveButton("OK"){
                        text, listener ->
                    //Do Nothing
                }
                dialog.setNegativeButton("Cancel") {
                        text, listener ->
                }
                dialog.create()
                dialog.show()
            }
        }

        return view
    }
}