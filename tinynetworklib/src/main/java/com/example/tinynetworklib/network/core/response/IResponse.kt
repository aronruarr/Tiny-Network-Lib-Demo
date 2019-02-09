package com.example.tinynetworklib.network.core.response

import com.example.tinynetworklib.network.core.request.IRequest

interface IResponse {

    var request: IRequest

    var body: ByteArray

}