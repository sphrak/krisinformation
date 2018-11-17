package fi.kroon.krisinformation.di.component

import android.app.Application
import dagger.Component
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import fi.kroon.krisinformation.di.modules.ApiModule
import fi.kroon.krisinformation.di.modules.NetworkModule
import fi.kroon.krisinformation.di.modules.SchedulerModule
import fi.kroon.krisinformation.di.modules.ViewModelModule
import fi.kroon.krisinformation.di.modules.DatabaseModule
import fi.kroon.krisinformation.presentation.CapMessageFragment
import fi.kroon.krisinformation.presentation.AboutFragment
import fi.kroon.krisinformation.presentation.FeedFragment
import fi.kroon.krisinformation.presentation.LinkFragment
import fi.kroon.krisinformation.presentation.MainActivity
import fi.kroon.krisinformation.presentation.ThemeFragment

@KrisAppScope
@Component(
    modules = [
        NetworkModule::class,
        ApiModule::class,
        DatabaseModule::class,
        SchedulerModule::class,
        ViewModelModule::class
    ]
)
interface KrisInformationComponent {
    fun inject(application: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(feedFragment: FeedFragment)
    fun inject(linkFragment: LinkFragment)
    fun inject(themeFragment: ThemeFragment)
    fun inject(capMessageFragment: CapMessageFragment)
    fun inject(aboutFragment: AboutFragment)
}