package fi.kroon.krisinformation.data.features.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Feature(

    @Json(name = "Id")
    val id: String,

    @Json(name = "Headline")
    val headline: String,

    @Json(name = "Preamble")
    val preamble: String,

    @Json(name = "BodyText")
    val bodyText: String,

    @Json(name = "Images")
    val images: Images,

    @Json(name = "ImageCaption")
    val imageCaption: String,

    @Json(name = "VideoUrl")
    val videoUrl: String,

    @Json(name = "VideoCaption")
    val videoCaption: String,

    @Json(name = "Checklist")
    val checkList: List<Checklist>,

    @Json(name = "Facts")
    val facts: String,

    @Json(name = "Links")
    val links: List<Link>,

    @Json(name = "ChangedDate")
    val changedDate: String,

    @Json(name = "SortOrder")
    val sortOrder: Int

)