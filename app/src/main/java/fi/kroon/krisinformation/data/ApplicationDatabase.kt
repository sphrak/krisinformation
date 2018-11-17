package fi.kroon.krisinformation.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fi.kroon.krisinformation.data.filter.local.FilterLocalDataSource
import fi.kroon.krisinformation.data.filter.model.Filter

@Database(
    version = DATABASE_VERSION,
    entities = [
        Filter::class
    ]
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun filterLocalDataSource(): FilterLocalDataSource
}