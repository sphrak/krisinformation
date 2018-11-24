package fi.kroon.krisinformation.di.modules

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import fi.kroon.krisinformation.common.NetworkHandler
import fi.kroon.krisinformation.data.BASE_API_URL_V1
import fi.kroon.krisinformation.data.BASE_API_URL_V2
import fi.kroon.krisinformation.data.capmessage.CapMessageRepository
import fi.kroon.krisinformation.data.capmessage.net.CapMessageApi
import fi.kroon.krisinformation.data.features.FeaturesRepository
import fi.kroon.krisinformation.data.features.net.FeaturesApi
import fi.kroon.krisinformation.data.feed.FeedRepository
import fi.kroon.krisinformation.data.feed.net.FeedApi
import fi.kroon.krisinformation.data.filter.FilterRepository
import fi.kroon.krisinformation.data.filter.local.FilterLocalDataSource
import fi.kroon.krisinformation.data.links.LinksRepository
import fi.kroon.krisinformation.data.links.net.LinksApi
import fi.kroon.krisinformation.data.theme.ThemeRepository
import fi.kroon.krisinformation.data.theme.net.ThemeApi
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import fi.kroon.krisinformation.di.qualifiers.V1
import fi.kroon.krisinformation.di.qualifiers.V2
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {

    @Module
    companion object {

        // REPOSITORY

        @Provides
        @JvmStatic
        @KrisAppScope
        fun capMessageRepository(capMessageApi: CapMessageApi, networkHandler: NetworkHandler) = CapMessageRepository(capMessageApi, networkHandler)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun feedRepository(feedApi: FeedApi, networkHandler: NetworkHandler) = FeedRepository(feedApi, networkHandler)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun themeRepository(themeApi: ThemeApi, networkHandler: NetworkHandler) = ThemeRepository(themeApi, networkHandler)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun linkRepository(linksApi: LinksApi, networkHandler: NetworkHandler) = LinksRepository(linksApi, networkHandler)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun featuresRepository(featuresApi: FeaturesApi, networkHandler: NetworkHandler) = FeaturesRepository(featuresApi, networkHandler)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun filterRepository(filterLocalDataSource: FilterLocalDataSource) = FilterRepository(filterLocalDataSource)

        // API V1
        @Provides
        @JvmStatic
        @KrisAppScope
        fun capMessageApi(@V1 retrofit: Retrofit) = retrofit.create(CapMessageApi::class.java)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun themeApi(@V1 retrofit: Retrofit) = retrofit.create(ThemeApi::class.java)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun linkApi(@V1 retrofit: Retrofit) = retrofit.create(LinksApi::class.java)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun feedApi(@V1 retrofit: Retrofit) = retrofit.create(FeedApi::class.java)

        // API V2
        @Provides
        @JvmStatic
        @KrisAppScope
        fun featuresApi(@V2 retrofit: Retrofit) = retrofit.create(FeaturesApi::class.java)

        @Provides
        @JvmStatic
        @KrisAppScope
        fun moshi(): Moshi = Moshi
            .Builder()
            .build()

        @V1
        @Provides
        @JvmStatic
        @KrisAppScope
        fun retrofitV1(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_API_URL_V1)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
        }

        @V2
        @Provides
        @JvmStatic
        @KrisAppScope
        fun retrofitV2(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_API_URL_V2)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
        }
    }
}