package hr.perisic.luka.data.api.socket

import io.socket.client.IO

internal class InfoSocketImpl {



    private val NEW_MESSAGE = "NEW_MESSAGE"

    init {

        val socket = IO.socket("https://...")

        socket.connect()

        socket.on(NEW_MESSAGE) {
            //on new message received
        }

    }
}