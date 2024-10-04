package com.example.vittoassignment.connectivity

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {

    val isNetworkAvailable:Flow<Boolean>

//    fun isNetworkAvailable() : Flow<Boolean>


}