package com.example.tmbdassesment.network

import java.io.IOException

class NoNetworkException : IOException() {
    override val message: String
        get() = "Tidak koneksi internet, silahkan dicoba lagi nanti"
}