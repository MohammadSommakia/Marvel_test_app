package com.mylivn.commons.ui.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mylivn.domain.models.base.Failure
import com.mylivn.domain.models.base.State

/**
 * Base [ViewModel] for view models that will process data.
 *
 * This view model provides state & error with [stateData] & [errorData] respectively.
 */
abstract class BaseViewModel<T> : ViewModel() {

    //region:properties
    private val _successData = MutableLiveData<T>()
    private val _stateData = MutableLiveData<State>()
    private val _errorData = MutableLiveData<Failure?>()

    /**
     * Observe [successData] to get notified of data if it's successfully fetched
     */
    val successData: LiveData<T>
        get() = _successData

    /**
     * Observe [stateData] to get notified of state of data
     */
    val stateData: LiveData<State>
        get() = _stateData

    /**
     * Observe [errorData] to get the error message
     */
    val errorData: LiveData<Failure?>
        get() = _errorData


    /**
     * Observe [isLoading] to get notified when there is ongoing network request
     */
    val isLoading: LiveData<Boolean> = Transformations.map(stateData) {
        it == State.Loading
    }

    /**
     * Observe [isFailed] to get notified if an error occurs
     */
    val isFailed: LiveData<Boolean> = Transformations.map(isLoading) {
        !it && errorData.value != null
    }

    //endregion

    //region Functions

    /**
     * Default success handler which assigns given [data] to [successData]
     *
     * @param data success data
     */
    protected fun handleSuccess(data: T) {
        _successData.value = data!!
    }

    /**
     * Default state handler which assigns given [state] to [stateData]
     *
     * @param state state of operation
     */
    protected fun handleState(state: State) {
        _stateData.value = state
    }

    /**
     * Default error handler which assigns received [error] to [errorData]
     *
     * @param failure [Failure] error data
     */
    protected fun handleFailure(failure: Failure?) {
        _errorData.value = failure
    }

    /**
     * Error handler which assigns received [error] to [errorData] & logs the error event using the AnalyticsHelper
     *
     * @param failure [Failure] error data
     * @param screen name of screen
     * @param analyticsHelper used to log events
     */
    protected fun handleAndLogFailure(
        failure: Failure?
    ) {
        _errorData.value = failure
        failure?.let {
        }
    }
    //endregion
}