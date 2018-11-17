package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.links.LinksRepository
import fi.kroon.krisinformation.data.links.model.Links
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class LinksUseCase @Inject constructor(
    private val linksRepository: LinksRepository
) {
    fun get(forceCacheInvalidation: Boolean = false): Single<Either<Failure, Links>> {
        return linksRepository
            .get()
            .doOnError {
                Timber.d("$it")
            }
    }
}