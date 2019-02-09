package com.example.tinynetworklibdemo

import android.app.Application
import com.example.tinynetworklib.network.provider.UrlNetworkProvider

class App : Application() {

    override fun onTerminate() {
        super.onTerminate()
        networkProvider.terminate()
    }

    companion object {
        val networkProvider = UrlNetworkProvider()
    }

}