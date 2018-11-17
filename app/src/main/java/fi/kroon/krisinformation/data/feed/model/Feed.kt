package fi.kroon.krisinformation.data.feed.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Feed(

    @Json(name = "ID")
    val id: String,

    @Json(name = "Updated")
    val updated: String,

    @Json(name = "Published")
    val published: String,

    @Json(name = "CapEvent")
    val capEvent: String,

    @Json(name = "Author")
    val author: Author,

    @Json(name = "Title")
    val title: String,

    @Json(name = "Link")
    val link: Link,

    @Json(name = "Summary")
    val summary: String,

    @Json(name = "CapArea")
    val capArea: List<CapArea>

)