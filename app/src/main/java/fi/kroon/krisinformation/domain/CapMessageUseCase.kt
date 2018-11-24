package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.common.extensions.splitToList
import fi.kroon.krisinformation.data.SENDER_NAME
import fi.kroon.krisinformation.data.capmessage.CapMessageRepository
import fi.kroon.krisinformation.data.capmessage.model.CapMessage
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.filter.model.Filter
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.functional.map
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class CapMessageUseCase @Inject constructor(
    private val capMessageRepository: CapMessageRepository,
    private val filterUseCase: FilterUseCase
) {

    private data class Data(
        val capMessageList: List<CapMessage>,
        val filterList: List<Filter>
    )

    fun get(forceCacheInvalidation: Boolean = false): Single<Either<Failure, List<CapMessage>>> {
        return capMessageRepository
            .get(forceCacheInvalidation)
            .map(::createFilterList)
            .flatMap(::saveFilterList)
            .doOnError {
                Timber.d("$it")
            }
    }

    private fun createFilterList(eitherCapMessageList: Either<Failure, List<CapMessage>>): Either<Failure, Data> =
        eitherCapMessageList.map { capMessageList ->

            val filterList = capMessageList.flatMap { capMessage ->

                with(capMessage) {

                    infoData.first()
                        .senderName
                        ?.splitToList()
                        ?.map { sender ->
                            Filter(
                                name = sender,
                                displayName = infoData
                                    .first()
                                    .category,
                                type = SENDER_NAME
                            )
                        }!!
                    /*?.filter { filter ->
                        filter.name.isNotEmpty()
                            || filter.name.isNotEmpty()
                    }*/
                }
            }.distinct()

            Data(
                capMessageList = capMessageList,
                filterList = filterList
            )

        }

    private fun saveFilterList(eitherData: Either<Failure, Data>)
        : Single<out Either<Failure, List<CapMessage>>> =

        eitherData.either(
            { failure ->
                Single.fromCallable {
                    Either.Left(failure)
                }
            }, { data ->
                filterUseCase
                    .insert(data.filterList)
                    .map { either ->
                        either.map { data.capMessageList }
                    }
            }
        )
}