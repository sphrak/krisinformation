package fi.kroon.krisinformation.data.feed.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Author(
    @Json(name = "Name")
    val name: String
)