package fi.kroon.krisinformation

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.squareup.leakcanary.LeakCanary
import fi.kroon.krisinformation.di.component.DaggerKrisInformationComponent
import fi.kroon.krisinformation.di.component.KrisInformationComponent
import fi.kroon.krisinformation.di.modules.ApplicationModule
import timber.log.Timber

abstract class BaseApplication : Application() {

    val cmp: KrisInformationComponent by lazy {
        DaggerKrisInformationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        cmp.inject(this)
        plantTimber()
        initThreeTenAbp()
        initLeakCanary()
    }

    private fun plantTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initLeakCanary() {
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) return
            LeakCanary.install(this)
        }
    }

    private fun initThreeTenAbp() {
        AndroidThreeTen.init(this)
    }
}