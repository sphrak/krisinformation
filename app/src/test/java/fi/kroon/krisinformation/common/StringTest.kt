package fi.kroon.krisinformation.common

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.common.extensions.parseToLocalDate
import fi.kroon.krisinformation.common.extensions.splitByCommaTakeFirst
import fi.kroon.krisinformation.common.extensions.splitBySpaceTakeFirst
import fi.kroon.krisinformation.common.extensions.splitToList
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime

class StringTest : BaseUnitTest() {

    @Test
    fun `concatenated String is split into list of strings`() {
        val listOfString = listOf("a", "b", "c", "d", "e")
        val data = "a, b, c, d, e"
        val result = data.splitToList()

        assertThat(result).isInstanceOf(List::class.java)
        assertThat(result).isEqualTo(listOfString)
        assertThat(result.size).isEqualTo(listOfString.size)
    }

    @Test
    fun `concatenated string by comma is split and first String returned`() {
        val data = "a, b, c, d, e"
        val result = data.splitByCommaTakeFirst()

        assertThat(result).isInstanceOf(String::class.java)
        assertThat(result).isEqualTo("a")
    }

    @Test
    fun `concatenated string by space is split and first String returned`() {
        val data = "a b c d e"
        val result = data.splitBySpaceTakeFirst()

        assertThat(result).isInstanceOf(String::class.java)
        assertThat(result).isEqualTo("a").isNotEqualTo("b")
    }

    @Test
    fun `parse string into localdate`() {
        val localDate = OffsetDateTime.parse("2018-11-22T22:44:50+01:00").toLocalDate()
        val data = "2018-11-22T22:44:50+01:00"
        val result = data.parseToLocalDate()

        assertThat(result).isInstanceOf(LocalDate::class.java)
        assertThat(result).isEqualTo(localDate)
    }
}