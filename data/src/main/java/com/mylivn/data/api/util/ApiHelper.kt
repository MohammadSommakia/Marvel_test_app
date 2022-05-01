package com.mylivn.data.api.util

import com.mylivn.data.R
import com.mylivn.domain.models.base.Failure
import com.mylivn.domain.models.base.Success
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

const val TIMEOUT_ERROR_STATUS_CODE = 408
const val NO_INTERNET_CONNECTION_STATUS_CODE = 444

class ApiHelper @Inject constructor(private val networkInfoHelper: NetworkInfoHelper) {


    val isNetworkConnected: Boolean
        get() = networkInfoHelper.isInternetAvailable()

    inline fun <T, R> safeExecute(
        block: () -> Response<T>,
        transform: (T) -> R,
        persist: (R) -> R
    ) =
        if (isNetworkConnected) {
            try {
                block().extractResponseBody(transform, persist)
            } catch (e: IOException) {
                Failure(
                    messageRes =  com.mylivn.data.R.string.reason_timeout,
                    code = TIMEOUT_ERROR_STATUS_CODE
                )
            }
        } else {
            Failure(
                messageRes = com.mylivn.data.R.string.reason_network,
                code = NO_INTERNET_CONNECTION_STATUS_CODE
            )
        }

    inline fun <T, R> Response<T>.extractResponseBody(transform: (T) -> R, persist: (R) -> R) =
        if (isSuccessful) {
            body()?.let {

                val mappedResponse = transform(it)
                Success(persist(mappedResponse))
            } ?: Failure(
                messageRes = com.mylivn.data.R.string.reason_network,
                code = TIMEOUT_ERROR_STATUS_CODE
            )
        } else {
            val errorBody = errorBody()?.string() ?: ""
            val code = code()
            try {
                handleError(JSONObject(errorBody), code)
            } catch (e: Exception) {
                if (errorBody.isNotEmpty()) {
                    Failure(message = errorBody, code = code)
                } else {
                    Failure(
                        messageRes =  com.mylivn.data.R.string.reason_generic,
                        code = code
                    )
                }
            }
        }

    /**
     * Return error messages according to the error code
     */
    fun handleError(json: JSONObject, code: Int): Failure {
        return when (json.getInt("Code")) {
            500 -> {
                Failure(messageRes =  R.string.reason_response, code = code)
            }
            else -> {
                Failure(message = json.getString("Message"), code = code)
            }
        }
    }
}