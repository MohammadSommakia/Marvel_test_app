package com.mylivn.data.api.util

import java.net.InetAddress
import java.net.UnknownHostException
import javax.inject.Inject


class NetworkInfoHelper @Inject constructor() {

    fun isInternetAvailable(): Boolean {
        try {
            val address: InetAddress = InetAddress.getByName("www.google.com")
            return !address.equals("")
        } catch (e: UnknownHostException) {
        }
        return false
    }

}