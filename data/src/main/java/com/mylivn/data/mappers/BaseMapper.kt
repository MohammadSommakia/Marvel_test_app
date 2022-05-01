package com.mylivn.data.mappers

/**
 * [D] is the data transform object retrieved from the server
 * [T] is the object that will be presented in the app
 */
interface BaseMapper<D, T> {
    /**
     * Maps the DTO [D] into [T]
     */
    fun map(response: D): T
}