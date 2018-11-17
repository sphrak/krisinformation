package fi.kroon.krisinformation.data.filter.local

import androidx.room.Dao
import androidx.room.Query
import fi.kroon.krisinformation.data.BaseLocalDataSource
import fi.kroon.krisinformation.data.filter.model.Filter
import io.reactivex.Single

@Dao
abstract class FilterLocalDataSource : BaseLocalDataSource<Filter> {

    @Query("""
        SELECT  *
        FROM    filter
    """)
    abstract fun get(): Single<List<Filter>>
}