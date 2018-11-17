package fi.kroon.krisinformation.common

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.data.functional.Either
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EitherTest : BaseUnitTest() {

    @Test
    fun `Either Right should return correct type`() {
        val result = Either.Right("hackerman")

        assertThat(result).isInstanceOf(Either::class.java)
        assertThat(result.isRight).isEqualTo(true)
        assertThat(result.isLeft).isEqualTo(false)

        result.either(
            {},
            { right -> assertThat(right).isInstanceOf(String::class.java)
                assertThat(right).isEqualTo("hackerman")
            }
        )
    }

    @Test
    fun `Either Left should return correct type`() {
        val result = Either.Left("hackerman failed")

        assertThat(result).isInstanceOf(Either::class.java)
        assertThat(result.isRight).isEqualTo(false)
        assertThat(result.isLeft).isEqualTo(true)

        result.either(
            { left -> assertThat(left).isInstanceOf(String::class.java)
                assertThat(left).isEqualTo("hackerman failed")
            },
            {}
        )
    }
}