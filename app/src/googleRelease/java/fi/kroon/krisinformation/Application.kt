package fi.kroon.krisinformation

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric

class Application : BaseApplication() {

    companion object {
        operator fun get(context: Context): Application {
            return context.applicationContext as Application
        }
    }

    override fun onCreate() {
        super.onCreate()
        initCrashlytics()
    }

    private fun initCrashlytics() {
        val crashlyticsKit = Crashlytics.Builder()
            .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
            .build()
        Fabric.with(this, crashlyticsKit)
    }
}