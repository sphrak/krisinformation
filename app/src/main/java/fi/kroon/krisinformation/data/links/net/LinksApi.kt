package fi.kroon.krisinformation.data.links.net

import fi.kroon.krisinformation.data.DEFAULT_FORMAT
import fi.kroon.krisinformation.data.HEADER_CACHE_CONTROL
import fi.kroon.krisinformation.data.links.model.Links
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LinksApi {

    @GET("links")
    fun get(
        @Query("format") format: String = DEFAULT_FORMAT,
        @Header(HEADER_CACHE_CONTROL) cacheControl: String? = null
    ): Single<Response<Links>>
}