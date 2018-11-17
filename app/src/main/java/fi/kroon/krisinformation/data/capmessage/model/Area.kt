package fi.kroon.krisinformation.data.capmessage.model

import com.squareup.moshi.Json

data class Area(
    @Json(name = "AreaDesc")
    val areaDesc: String,

    @Json(name = "Type")
    val type: String,

    @Json(name = "Polygon")
    val polygon: List<Polygon>
)