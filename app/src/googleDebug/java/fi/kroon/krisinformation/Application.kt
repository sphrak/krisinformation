package fi.kroon.krisinformation

import android.content.Context

class Application : BaseApplication() {

    companion object {
        operator fun get(context: Context): Application {
            return context.applicationContext as Application
        }
    }
}