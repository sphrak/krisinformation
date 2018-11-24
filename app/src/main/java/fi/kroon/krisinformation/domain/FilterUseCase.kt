package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.common.Schedulers
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.filter.FilterRepository
import fi.kroon.krisinformation.data.filter.model.Filter
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class FilterUseCase @Inject constructor(
    private val filterRepository: FilterRepository,
    private val schedulers: Schedulers
) {
    fun get(): Single<Either<Failure, List<Filter>>> = filterRepository.get()

    fun insert(filterList: List<Filter>): Single<Either<Failure, List<Long>>> =
        filterRepository.insert(filterList)

}