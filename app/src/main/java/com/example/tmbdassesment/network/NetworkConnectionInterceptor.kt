package com.example.tmbdassesment.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoNetworkException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    @SuppressLint("MissingPermission")
    fun isConnected(): Boolean {
        var result = false
        var connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivity.activeNetwork ?: return false
            val actNw =
                connectivity.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivity.run {
                activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }

}