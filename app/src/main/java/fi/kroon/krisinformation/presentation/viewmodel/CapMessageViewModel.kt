package fi.kroon.krisinformation.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import fi.kroon.krisinformation.common.Schedulers
import fi.kroon.krisinformation.data.capmessage.model.CapMessage
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import fi.kroon.krisinformation.domain.CapMessageUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

@KrisAppScope
class CapMessageViewModel @Inject constructor(
    private val schedulers: Schedulers,
    private val capMessageUseCase: CapMessageUseCase
) : BaseViewModel() {

    var capMessageResponse: MutableLiveData<List<CapMessage>> = MutableLiveData()

    fun get() = capMessageUseCase
        .get(forceCacheInvalidation)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .onErrorReturn { Either.Left(Failure.IOException()) }
        .subscribe(
            { it.either(::handleFailure, ::handleCapMessageResponse) },
            { Timber.d("CapMessage failed") }
        ).also {
            forceCacheInvalidation = false
        }.addTo(subscriptions)

    private fun handleCapMessageResponse(capMessageList: List<CapMessage>) {
        this.capMessageResponse.value = capMessageList
    }
}