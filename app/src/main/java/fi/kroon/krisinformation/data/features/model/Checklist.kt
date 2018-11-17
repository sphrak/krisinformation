package fi.kroon.krisinformation.data.features.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Checklist(

    @Json(name = "Title")
    val title: String,

    @Json(name = "Preamble")
    val preamble: String? = "",

    @Json(name = "Bullets")
    val bullets: List<String>

)