package com.example.tinynetworklib.network.provider

import com.example.tinynetworklib.network.core.request.IRequest
import com.example.tinynetworklib.network.core.response.Response
import java.io.BufferedInputStream
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.PriorityBlockingQueue


class UrlNetworkProvider : INetworkProvider {

    private val executor = Executors.newSingleThreadExecutor()

    private val queue = PriorityBlockingQueue<IRequest>(16) { lhs, rhs -> rhs.priority.compareTo(lhs.priority) }

    override fun call(request: IRequest, callback: ICallback): ICall {
        if (request.uri.isEmpty()) {
            throw IllegalArgumentException("The request uri is empty!")
        }

        queue.offer(request)

        val future = executor.submit {
            val item = queue.poll()

            try {
                val data = readAll(URL(item.uri))
                callback.onSuccess(Response(item, data))
            } catch (e: Exception) {
                callback.onError(e)
            }
        }

        return Call(future)
    }

    override fun terminate() {
        executor.shutdownNow()
        queue.clear()
    }

    protected fun readAll(url: URL, timeout: Int = 5000) = with(url.openConnection()) {
        connectTimeout = timeout
        readTimeout = timeout
        connect()

        getInputStream().use { stream ->
            BufferedInputStream(stream).use { reader ->
                return@with reader.readBytes()
            }
        }
    }

}