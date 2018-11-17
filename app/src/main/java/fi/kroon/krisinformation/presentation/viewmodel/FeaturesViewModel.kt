package fi.kroon.krisinformation.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import fi.kroon.krisinformation.common.Schedulers
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.features.model.Features
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import fi.kroon.krisinformation.domain.FeaturesUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

@KrisAppScope
class FeaturesViewModel @Inject constructor(
    private val schedulers: Schedulers,
    private val featuresUseCase: FeaturesUseCase
) : BaseViewModel() {

    var features: MutableLiveData<Features> = MutableLiveData()

    fun get() = featuresUseCase
        .get(forceCacheInvalidation)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .onErrorReturn { Either.Left(Failure.IOException()) }
        .subscribe(
            { it.either(::handleFailure, ::handleFeatures) },
            { Timber.d("Feed failed") }
        ).also {
            forceCacheInvalidation = false
        }.addTo(subscriptions)

    private fun handleFeatures(features: Features) = {
        this.features.value = features
    }
}