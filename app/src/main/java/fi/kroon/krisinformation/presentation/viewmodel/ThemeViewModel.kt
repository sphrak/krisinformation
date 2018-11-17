package fi.kroon.krisinformation.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import fi.kroon.krisinformation.common.Schedulers
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.theme.model.ThemeResponse
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import fi.kroon.krisinformation.domain.ThemeUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

@KrisAppScope
class ThemeViewModel @Inject constructor(
    private val schedulers: Schedulers,
    private val themeUseCase: ThemeUseCase
) : BaseViewModel() {

    var themeResponse: MutableLiveData<ThemeResponse> = MutableLiveData()

    fun get() = themeUseCase
        .get(forceCacheInvalidation)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .onErrorReturn { Either.Left(Failure.RuntimeException()) }
        .subscribe(
            { it.either(::handleFailure, ::handleThemeResponse) },
            { Timber.d("Theme failed") }
        ).also {
            forceCacheInvalidation = false
        }.addTo(subscriptions)

    private fun handleThemeResponse(themeResponse: ThemeResponse) {
        this.themeResponse.value = themeResponse
    }
}