package fi.kroon.krisinformation.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import fi.kroon.krisinformation.common.Schedulers
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.links.model.Links
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import fi.kroon.krisinformation.domain.LinksUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

@KrisAppScope
class LinksViewModel @Inject constructor(
    private val schedulers: Schedulers,
    private val linksUseCase: LinksUseCase
) : BaseViewModel() {

    var links: MutableLiveData<Links> = MutableLiveData()

    fun get() = linksUseCase
        .get(forceCacheInvalidation)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .onErrorReturn { Either.Left(Failure.RuntimeException()) }
        .subscribe(
            { it.either(::handleFailure, ::handleLink) },
            { Timber.d("Link data failed") }
        ).also {
            forceCacheInvalidation = false
        }.addTo(subscriptions)

    private fun handleLink(links: Links) {
        this.links.value = links
    }
}