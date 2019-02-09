package com.example.tinynetworklib.network.core.response

import com.example.tinynetworklib.network.core.request.IRequest


class Response(
    override var request: IRequest,
    override var body: ByteArray
) : IResponse