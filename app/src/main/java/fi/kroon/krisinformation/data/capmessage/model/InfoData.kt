package fi.kroon.krisinformation.data.capmessage.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoData(

    @Json(name = "Language")
    val language: String,

    @Json(name = "Category")
    val category: String,

    @Json(name = "Event")
    val event: String,

    @Json(name = "Urgency")
    val urgency: String,

    @Json(name = "Severity")
    val severity: String,

    @Json(name = "Certainty")
    val certainty: String,

    @Json(name = "Headline")
    val headline: String,

    @Json(name = "Description")
    val description: String,

    @Json(name = "SenderName")
    val senderName: String?,

    @Json(name = "Web")
    val web: String,

    @Json(name = "Area")
    val area: List<Area>?
)