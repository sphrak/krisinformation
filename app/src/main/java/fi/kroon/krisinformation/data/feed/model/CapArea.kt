package fi.kroon.krisinformation.data.feed.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CapArea(

    @Json(name = "CapAreaDesc")
    val capAreaDesc: String,

    @Json(name = "Coordinate")
    val coordinate: String
)