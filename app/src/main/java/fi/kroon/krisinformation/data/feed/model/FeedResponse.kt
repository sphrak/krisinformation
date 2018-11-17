package fi.kroon.krisinformation.data.feed.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedResponse(
    @Json(name = "Entries")
    val entries: List<Feed>
)