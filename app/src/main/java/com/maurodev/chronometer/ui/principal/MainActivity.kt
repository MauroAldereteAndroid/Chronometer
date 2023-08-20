package com.maurodev.chronometer.ui.principal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maurodev.chronometer.databinding.ActivityMainBinding
import com.maurodev.chronometer.utils.StringUtils

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _vm: MainViewModel
    private var flagStatusPlay = false
    private var flagStatusPause = false
    private var time: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _vm = ViewModelProvider(this)[MainViewModel::class.java]

        behaviorButtons()
        observers()
    }

    /**
     * Function in charge of observing the changes of the View Model.
     */
    private fun observers() {
        // This value changes the text of the button
        _vm.flagButtonPlay.observe(this) { flagPlay ->
            flagStatusPlay = flagPlay
            _binding.bPlay.text = if (flagPlay) LOOP else PLAY
        }

        // This boolean changes the status if it is paused or not
        _vm.flagButtonPause.observe(this) { flagStatusPause = it }

        // This value updates the time in long
        _vm.currentTimeLong.observe(this) {
            time = it
            _binding.tvCurrentTime.text = StringUtils.formatTime(it)
        }

        _vm.newLoops.observe(this) {
            setupRecyclerView(_binding.root.context, it)
        }

    }

    private fun setupRecyclerView(context: Context, loopList: MutableList<String>) {
        val adapter = RecyclerViewAdapter(loopList)
        _binding.rvTimeCicles.layoutManager = LinearLayoutManager(context)
        _binding.rvTimeCicles.adapter = adapter

        // Keeps focus on the latest added value
        val lastItemPosition = adapter.itemCount - 1
        _binding.rvTimeCicles.scrollToPosition(lastItemPosition)
    }

    /**
     * Function that manages the behavior of the buttons while the user is manipulating.
     */
    private fun behaviorButtons() {
        _binding.bPlay.setOnClickListener {
            if (time == null) {
                flagStatusPlay = false
                flagStatusPause = true
            }

            _vm.continueChronometer()
            _vm.setPlayFlagValue(true)

            if (!flagStatusPlay && flagStatusPause) {
                _vm.startChronometer()
            }
        }

        _binding.bPause.setOnClickListener {
            _vm.setPlayFlagValue(false)
            _vm.setPauseFlagValue(true)
            _vm.pauseChronometer()
        }

        _binding.bStop.setOnClickListener {
            _vm.setPlayFlagValue(false)
            _vm.stopChronometer()
        }
    }

    companion object {
        const val LOOP = "Loop"
        const val PLAY = "Play"
    }
}