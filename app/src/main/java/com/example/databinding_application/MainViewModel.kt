package com.example.databinding_application

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


class MainViewModel() : ViewModel() {

    private var viewModelJob: Job? = null
    private var initialTime = 0L
    private val DELAY = 1000L
    val stopwatch = MutableLiveData<String>("00:00:00")

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun start() {
        if(viewModelJob == null) {
            viewModelJob = coroutineScope.launch {
                while (true) {
                    delay(DELAY)
                    val startTime = initialTime + DELAY/1000
                    postStopWatch(startTime)
                    initialTime = startTime
                }
            }
        }
    }

    fun stop() {
        viewModelJob?.cancel()
        viewModelJob = null
    }

    fun reset() {
        postStopWatch(0)
        viewModelJob?.cancel()
        viewModelJob = null
    }

    private fun postStopWatch(startTime: Long) {
        val hours: Long = startTime / 3600
        val minutes: Long = startTime % 3600 / 60
        val seconds: Long = startTime % 60
        val timeInHHMMSS = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        stopwatch.postValue(timeInHHMMSS)
    }
}