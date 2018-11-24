package fi.kroon.krisinformation.common

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.common.extensions.asObservable
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ObservableTest : BaseUnitTest() {

    @Test
    fun `turn sequence into observable`() {

        val testSequence = listOf("1", "2", "3", "4", "5")
        val testObservableSequence = testSequence.asObservable()

        assertThat(testObservableSequence).isInstanceOf(Observable::class.java)
    }
}