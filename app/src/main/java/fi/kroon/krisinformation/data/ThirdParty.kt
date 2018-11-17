package fi.kroon.krisinformation.data

data class ThirdParty(
    val author: String,
    val title: String,
    val page: String,
    val source: String,
    val license: String,
    val description: String
)