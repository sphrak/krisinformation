package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.data.capmessage.CapMessageRepository
import fi.kroon.krisinformation.data.capmessage.model.CapMessage
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.exception.Failure
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class CapMessageUseCase @Inject constructor(
    private val capMessageRepository: CapMessageRepository
) {
    fun get(forceCacheInvalidation: Boolean = false): Single<Either<Failure, List<CapMessage>>> {
        return capMessageRepository
            .get(forceCacheInvalidation)
            .doOnError {
                Timber.d("$it")
            }
    }
}