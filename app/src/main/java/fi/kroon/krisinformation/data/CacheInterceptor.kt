package fi.kroon.krisinformation.data

import fi.kroon.krisinformation.common.NetworkHandler
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

@KrisAppScope
class CacheInterceptor @Inject constructor(private val networkHandler: NetworkHandler) : Interceptor {
    companion object {
        const val TWENTY_MIN_IN_SEC = 60 * 20
        const val HALF_AN_HOUR_IN_SEC = 60 * 30
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.d("Cache interceptor #intercept")

        val request = when {
            chain.request().header(HEADER_CACHE_CONTROL) != null -> {
                // There is already header attached, probably "no-cache" from pull to refresh
                chain.request()
            }
            networkHandler.isConnected -> {
                chain.request().newBuilder()
                    .header("Cache-Control", "public, max-age=$TWENTY_MIN_IN_SEC").build()
            }
            else -> {
                chain.request().newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$HALF_AN_HOUR_IN_SEC").build()
            }
        }

        return chain.proceed(request)
    }
}