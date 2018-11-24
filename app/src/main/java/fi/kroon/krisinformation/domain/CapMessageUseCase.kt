package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.common.extensions.splitToList
import fi.kroon.krisinformation.data.SENDER_NAME
import fi.kroon.krisinformation.data.capmessage.CapMessageRepository
import fi.kroon.krisinformation.data.capmessage.model.CapMessage
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.filter.model.Filter
import fi.kroon.krisinformation.data.functional.map
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class CapMessageUseCase @Inject constructor(
    private val capMessageRepository: CapMessageRepository,
    private val filterUseCase: FilterUseCase
) {
    fun get(forceCacheInvalidation: Boolean = false): Single<Either<Failure, List<CapMessage>>> {
        return capMessageRepository
            .get(forceCacheInvalidation)
            .doOnSuccess {
                it.map {
                    list -> list.map {
                        with(it) {
                            infoData.first().senderName?.splitToList()?.map { sender ->
                                Filter(
                                    name = sender,
                                    displayName = infoData.first().category,
                                    type = SENDER_NAME
                                )
                            }?.filter {
                                filter -> filter.name.isNotEmpty() || filter.name.isNotEmpty()
                            }
                        }
                    }
                    .distinct()
                    .map {
                        it?.let {
                            filterUseCase.insert(it)
                        }
                    }
                }
            }.doOnError {
                Timber.d("$it")
            }
    }
}