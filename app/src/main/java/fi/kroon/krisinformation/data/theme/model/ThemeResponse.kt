package fi.kroon.krisinformation.data.theme.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThemeResponse(
    @Json(name = "ThemeList")
    val themeList: List<Theme>
)