package fi.kroon.krisinformation.data.features.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Link(
    @Json(name = "Text")
    val text: String,

    @Json(name = "Url")
    val url: String
)