package fi.kroon.krisinformation.data.capmessage.model

import com.squareup.moshi.Json

data class Polygon(
    @Json(name = "Polygons")
    val polygons: String
)