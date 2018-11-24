package fi.kroon.krisinformation.common

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.common.extensions.asSingle
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SingleTest : BaseUnitTest() {

    @Test
    fun `turn sequence into single`() {

        val testSequence = listOf("1", "2", "3", "4", "5")
        val testObservableSequence = testSequence.asSingle()

        assertThat(testObservableSequence).isInstanceOf(Single::class.java)
    }
}