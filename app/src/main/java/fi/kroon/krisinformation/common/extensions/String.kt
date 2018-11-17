package fi.kroon.krisinformation.common.extensions

import org.threeten.bp.OffsetDateTime

fun String.splitByCommaTakeFirst() = this.split(",").get(0).trim()
fun String.splitBySpaceTakeFirst() = this.split(" ").get(0).trim()
fun String.parseToLocalDate() = OffsetDateTime.parse(this).toLocalDate()