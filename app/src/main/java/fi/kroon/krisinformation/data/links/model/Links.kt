package fi.kroon.krisinformation.data.links.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(

    @Json(name = "Id")
    val id: Int,

    @Json(name = "LinkName")
    val linkName: String,

    @Json(name = "LinkUrl")
    val linkUrl: String,

    @Json(name = "Location")
    val location: String?,

    @Json(name = "Category")
    val category: String

)