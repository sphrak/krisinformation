package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.filter.FilterRepository
import fi.kroon.krisinformation.data.filter.model.Filter
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import javax.inject.Inject

class FilterUseCase @Inject constructor(
    private val filterRepository: FilterRepository
) {
    fun get(): Single<Either<Failure, List<Filter>>> = filterRepository.get()
}