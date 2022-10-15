@file:Suppress("DEPRECATION")

package com.intern.happyfood.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


class ConnectionManager {
    fun checkConnectivity(context: Context): Boolean{
        //Information about currently active internet
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

        return if (activeNetwork?.isConnected != null){
            activeNetwork.isConnected //True or false
        } else false //Incase of null for the broken or inactive internet

    }
}