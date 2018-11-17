package fi.kroon.krisinformation.data.feed.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Link(

    @Json(name = "Id")
    val id: String?,

    @Json(name = "LinkName")
    val linkName: String?,

    @Json(name = "LinkUrl")
    val linkUrl: String
)