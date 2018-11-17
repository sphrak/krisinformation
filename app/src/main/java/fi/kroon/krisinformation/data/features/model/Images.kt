package fi.kroon.krisinformation.data.features.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images(
    @Json(name = "Normal")
    val normal: String
)