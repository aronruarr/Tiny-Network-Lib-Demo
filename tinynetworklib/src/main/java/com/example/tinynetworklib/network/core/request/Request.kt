package com.example.tinynetworklib.network.core.request


class Request(
    override val uri: String = "",
    override val priority: Int = 5
) : IRequest