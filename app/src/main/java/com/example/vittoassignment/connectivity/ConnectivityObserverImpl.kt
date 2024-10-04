package com.example.vittoassignment.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConnectivityObserverImpl(context: Context):ConnectivityObserver {

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val _isNetworkAvailable = MutableStateFlow(false)
    override val isNetworkAvailable = _isNetworkAvailable.asStateFlow()

    init{
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _isNetworkAvailable.value = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _isNetworkAvailable.value = false
            }

            override fun onUnavailable() {
                super.onUnavailable()
                _isNetworkAvailable.value = false
            }
        })
    }

}