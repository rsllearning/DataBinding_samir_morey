package com.example.databinding_application


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mainViewModel = MainViewModel()

    @Test
     fun start_setStopWatchCorrectly() = runBlocking{
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel.stopwatch.observeForever {  }
        mainViewModel.start()
        delay(5000)
        assertThat(mainViewModel.stopwatch.value, `is`("00:00:04"))
        Dispatchers.resetMain()
    }

    @Test
    fun stop_stopsTheStopWatchCorrectly() = runBlocking{
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel.stopwatch.observeForever {  }
        mainViewModel.start()
        delay(5000)
        assertThat(mainViewModel.stopwatch.value, `is`("00:00:04"))
        mainViewModel.stop()
        delay(4000)
        assertThat(mainViewModel.stopwatch.value, `is`("00:00:05"))
        Dispatchers.resetMain()
    }

    @Test
    fun stop_resetsTheStopWatchCorrectly() = runBlocking{
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel.stopwatch.observeForever {  }
        mainViewModel.start()
        delay(5000)
        assertThat(mainViewModel.stopwatch.value, `is`("00:00:04"))
        mainViewModel.reset()
        assertThat(mainViewModel.stopwatch.value, `is`("00:00:00"))
        Dispatchers.resetMain()
    }
}