package fi.kroon.krisinformation.data.features.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Features(

    @Json(name = "Changed")
    val changed: List<Feature>,

    @Json(name = "All")
    val all: List<String>,

    @Json(name = "Timestamp")
    val timestamp: String

)