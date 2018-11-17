package fi.kroon.krisinformation.data.capmessage.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CapMessage(

    @Json(name = "Identifier")
    val identifier: String,

    @Json(name = "Sender")
    val sender: String,

    @Json(name = "Sent")
    val sent: String,

    @Json(name = "Published")
    val published: String,

    @Json(name = "Status")
    val status: String,

    @Json(name = "MsgType")
    val msgType: String,

    @Json(name = "Scope")
    val scope: String,

    @Json(name = "InfoData")
    val infoData: List<InfoData>,

    @Json(name = "Resources")
    val resources: Boolean?,

    @Json(name = "Active")
    val active: Boolean,

    @Json(name = "IsNewVMA")
    val isNewVma: Boolean? = false

)