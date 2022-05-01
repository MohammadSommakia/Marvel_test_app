package com.mylivn.domain.models.base


/**
 * Result class that wraps any [Success], [Failure] or [State]
 */
sealed class Result<out T>

//region:subclasses

class Success<out T>(val successData: T) : Result<T>()
class Failure(val messageRes: Int? = null, val message: String? = null, val code: Int? = null) :
    Result<Nothing>()

sealed class State : Result<Nothing>() {
    object Loading : State()
    object Loaded : State()
}

enum class ErrorCodes(val code: Int) {
    TIMEOUT(408)
}

//endregion

//region:extensions

inline fun <T> Result<T>.handle(
    stateBlock: (State) -> Unit,
    failureBlock: (Failure?) -> Unit,
    successBlock: (T) -> Unit
) {
    when (this) {
        is Success -> {
            failureBlock(null)
            successBlock(successData)
        }
        is Failure -> failureBlock(this)
        is State -> stateBlock(this)
    }
}

inline fun <T> Result<T>.onSuccess(successBlock: (T) -> Unit): Result<T> {
    if (this is Success)
        successBlock(successData)

    return this
}

inline fun <T> Result<T>.onFailure(errorBlock: (Int?, String?) -> Unit): Result<T> {
    if (this is Failure)
        errorBlock(messageRes, message)

    return this
}

inline fun <T> Result<T>.onState(stateBlock: (State) -> Unit): Result<T> {
    if (this is State)
        stateBlock(this)

    return this
}

//endregion