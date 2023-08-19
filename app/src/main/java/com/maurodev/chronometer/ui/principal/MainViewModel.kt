package com.maurodev.chronometer.ui.principal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurodev.chronometer.utils.StringUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel(), ChronometerBehaviorInterface {

    var flagButtonPlay = MutableLiveData<Boolean>()
    var flagButtonPause = MutableLiveData<Boolean>()
    var currentTimeLong = MutableLiveData<Long>()

    private var flagChronometerValue = false
    private var time = TIME_ZERO

    override fun startChronometer() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!flagChronometerValue) {
                flagChronometerValue = true
                time = TIME_ZERO
                chronometerTimer()
            }
        }
    }

    override fun stopChronometer() {
        viewModelScope.launch(Dispatchers.IO) {
            flagChronometerValue = false
            chronometerTimer()
        }
    }

    override fun pauseChronometer() {
        viewModelScope.launch(Dispatchers.IO) {
            flagChronometerValue = false
            chronometerTimer()
        }
    }

    override fun continueChronometer() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!flagChronometerValue) {
                flagChronometerValue = true
                chronometerTimer(time)
            }
            Log.i("LOOP", StringUtils.formatTime(time))
        }
    }

    /**
     * This function through a corutina works as a chronometer.
     * If newTime is null, the timer starts from zero.
     * In the case that already has a value, it continues from that value obtained.
     * @param newTime value currentTime in long.
     */
    private suspend fun chronometerTimer(newTime: Long? = null) {
        newTime?.let {
            withContext(Dispatchers.IO) {
                time = newTime
                while (flagChronometerValue) {
                    delay(LAPSE_TIME)
                    time += LAPSE_TIME
                    currentTimeLong.postValue(time)
                }
            }
        } ?: withContext(Dispatchers.IO) {
            while (flagChronometerValue) {
                delay(LAPSE_TIME)
                time += LAPSE_TIME
                currentTimeLong.postValue(time)
            }
        }
    }

    /**
     * This function lives in the viewModel to maintain the state of the button.
     */
    fun setPlayFlagValue(flag: Boolean) {
        flagButtonPlay.postValue(flag)
    }

    /**
     * This function lives in the viewModel to maintain the state of the button.
     */
    fun setPauseFlagValue(flag: Boolean) {
        flagButtonPause.postValue(flag)
    }

    companion object {
        private const val TIME_ZERO = 0L
        private const val LAPSE_TIME = 25L
    }
}