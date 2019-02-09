package com.example.tinynetworklib.network.provider

import java.util.concurrent.Future

class Call(private val future: Future<*>) : ICall {

    override fun cancel() {
        future.cancel(true)
    }

}