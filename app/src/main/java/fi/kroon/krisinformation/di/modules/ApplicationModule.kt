package fi.kroon.krisinformation.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import fi.kroon.krisinformation.di.component.scope.KrisAppScope

@Module
class ApplicationModule(val application: Application) {
    @Provides
    @KrisAppScope
    fun context(): Context = application
}