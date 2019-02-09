package com.example.tinynetworklib.network.provider

import com.example.tinynetworklib.network.core.response.IResponse

interface ICallback {

    fun onSuccess(response: IResponse)

    fun onError(e: Exception)

}