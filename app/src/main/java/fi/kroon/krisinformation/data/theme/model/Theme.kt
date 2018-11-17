package fi.kroon.krisinformation.data.theme.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Theme(

    @Json(name = "ID")
    val id: String,

    @Json(name = "Title")
    val title: String,

    @Json(name = "Text")
    val text: String,

    @Json(name = "ImageUrl")
    val imageUrl: String,

    @Json(name = "Links")
    val linkUrl: String,

    @Json(name = "Displaymode")
    val displayMode: String,

    @Json(name = "Emergency")
    val emergency: Boolean

)