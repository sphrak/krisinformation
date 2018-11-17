package fi.kroon.krisinformation.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import fi.kroon.krisinformation.data.ApplicationDatabase
import fi.kroon.krisinformation.data.DATABASE_NAME
import fi.kroon.krisinformation.di.component.scope.KrisAppScope

@Module(
    includes = [
        ApplicationModule::class
    ]
)
class DatabaseModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @KrisAppScope
        fun applicationDatabase(context: Context) = Room
            .databaseBuilder(context, ApplicationDatabase::class.java, DATABASE_NAME)
            .build()

        @Provides
        @JvmStatic
        @KrisAppScope
        fun filterLocalDataSource(applicationDatabase: ApplicationDatabase) = applicationDatabase.filterLocalDataSource()
    }
}