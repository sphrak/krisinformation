package fi.kroon.krisinformation.di.modules

import dagger.Module
import dagger.Provides
import fi.kroon.krisinformation.common.Schedulers
import fi.kroon.krisinformation.di.component.scope.KrisAppScope

@Module
class SchedulerModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        @KrisAppScope
        fun schedulers() = Schedulers()
    }
}