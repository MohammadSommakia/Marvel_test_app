package com.mylivn.domain.usecases.base

/**
 * Base use case for use cases that don't require any network calls
 */
abstract class BaseUseCase<T, in P : UseCaseParameters> {
    abstract suspend operator fun invoke(params: P): T
}

