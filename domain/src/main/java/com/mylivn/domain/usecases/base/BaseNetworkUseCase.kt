package com.mylivn.domain.usecases.base

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.base.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Base use case that wraps [suspending][suspend] [run] function with [flow][Flow] and returns it for later usage.
 */
/**
 * Use cases must extend this class if there're any API calls that need to be done
 */

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseNetworkUseCase<T, in P : UseCaseParameters> {

    abstract suspend fun FlowCollector<Result<T>>.run(params: P)

    operator fun invoke(params: P) =
        flow {
            emit(State.Loading)
            run(params)
            emit(State.Loaded)
        }.flowOn(Dispatchers.IO)
}


