package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.feed.FeedRepository
import fi.kroon.krisinformation.data.feed.model.FeedResponse
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class FeedUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    fun get(forceCacheInvalidation: Boolean = false): Single<Either<Failure, FeedResponse>> = feedRepository
        .get()
        .doOnError {
            Timber.d("$it")
        }
}