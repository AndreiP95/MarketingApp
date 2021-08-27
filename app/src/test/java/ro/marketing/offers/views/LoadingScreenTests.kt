package ro.marketing.offers.views

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.mockito.Mockito.*
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.Rule
import ro.marketing.offers.helper.CoroutineDispatcher
import ro.marketing.offers.views.customViews.LoadingDialog


@ExperimentalCoroutinesApi
class SplashScreenTests {

    private val spiedFragment by lazy {
        spy(LoadingDialog::class.java)
    }

    @get:Rule
    val coroutineRule = CoroutineDispatcher()


    @Test
    fun `check if coroutine has been launched `() {
        spiedFragment.onResume()

        verify(spiedFragment).startAnimation()
    }

    @Test
    fun `check if animation does not start on another coroutineContext `() {
        spiedFragment.onResume()
        coroutineRule.launch {
            verify(spiedFragment, times(0)).dotsAnimation()
        }
    }

    @Test
    fun `check if job has been instantiated `() {
        spiedFragment.onResume()
        assertNotEquals(spiedFragment.job, null)
    }

    @Test
    fun `check if job is null before on resume`() {
        spiedFragment.onStart()
        assertEquals(spiedFragment.job, null)
    }

}