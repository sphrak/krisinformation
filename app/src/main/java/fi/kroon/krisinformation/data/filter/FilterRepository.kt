package fi.kroon.krisinformation.data.filter

import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.filter.exception.FilterFailure
import fi.kroon.krisinformation.data.filter.local.FilterLocalDataSource
import fi.kroon.krisinformation.data.filter.model.Filter
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.functional.Either.Left
import fi.kroon.krisinformation.data.functional.Either.Right
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class FilterRepository @Inject constructor(
    private val filterLocalDataSource: FilterLocalDataSource
) {

    fun get(): Single<Either<Failure, List<Filter>>> =
        filterLocalDataSource
            .get()
            .map {
                Right(it) as Either<Failure, List<Filter>>
            }.doOnError {
                Timber.e("Filtering database failed.")
            }.onErrorReturn {
                Left(FilterFailure.FilteringFailed())
            }

    fun insert(filterList: List<Filter>): Single<Either<Failure, List<Long>>> =
        filterLocalDataSource.insert(filterList).map {
            Either.Right(it) as Either<Failure, List<Long>>
        }.onErrorReturn {
            Either.Left(FilterFailure.DatabaseInsertFailed())
        }
}