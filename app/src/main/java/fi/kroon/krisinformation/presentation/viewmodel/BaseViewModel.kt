package fi.kroon.krisinformation.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.kroon.krisinformation.data.FIFTEEN_SEC_IN_MILLIS
import fi.kroon.krisinformation.data.exception.Failure
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val subscriptions = CompositeDisposable()

    protected var lastCacheInvalidationTimestamp: Long = 0
    protected var forceCacheInvalidation = false

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    open fun forceCacheInvalidationForNextRequest() {
        if ((System.currentTimeMillis() - FIFTEEN_SEC_IN_MILLIS) <= lastCacheInvalidationTimestamp || lastCacheInvalidationTimestamp == 0L) {
            forceCacheInvalidation = true
            lastCacheInvalidationTimestamp = System.currentTimeMillis()
        }
    }
}