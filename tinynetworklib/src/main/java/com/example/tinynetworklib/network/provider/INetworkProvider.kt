package com.example.tinynetworklib.network.provider

import com.example.tinynetworklib.network.core.request.IRequest

interface INetworkProvider {

    fun call(request: IRequest, callback: ICallback): ICall

    fun terminate()

}