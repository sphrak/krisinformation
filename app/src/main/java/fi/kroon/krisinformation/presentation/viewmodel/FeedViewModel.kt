package fi.kroon.krisinformation.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import fi.kroon.krisinformation.common.Schedulers
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.feed.model.FeedResponse
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import fi.kroon.krisinformation.domain.FeedUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

@KrisAppScope
class FeedViewModel @Inject constructor(
    private val schedulers: Schedulers,
    private val feedUseCase: FeedUseCase
) : BaseViewModel() {

    var feedResponse: MutableLiveData<FeedResponse> = MutableLiveData()

    fun get() = feedUseCase
        .get(forceCacheInvalidation)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .onErrorReturn { Either.Left(Failure.RuntimeException()) }
        .subscribe(
            { it.either(::handleFailure, ::handleFeedResponse) },
            { Timber.d("Feed failed") }
        ).also {
            forceCacheInvalidation = false
        }.addTo(subscriptions)

    private fun handleFeedResponse(feedResponse: FeedResponse) {
        this.feedResponse.value = feedResponse
    }
}