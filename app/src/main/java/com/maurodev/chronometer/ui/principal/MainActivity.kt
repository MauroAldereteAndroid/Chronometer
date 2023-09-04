package com.maurodev.chronometer.ui.principal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maurodev.chronometer.databinding.ActivityMainBinding
import com.maurodev.chronometer.utils.BUTTON_CONTINUE_TEXT
import com.maurodev.chronometer.utils.BUTTON_LOOP_TEXT
import com.maurodev.chronometer.utils.BUTTON_PAUSE_TO_LOOP
import com.maurodev.chronometer.utils.BUTTON_PLAY_TO_CONTINUE
import com.maurodev.chronometer.utils.BUTTON_PLAY_TO_LOOP
import com.maurodev.chronometer.utils.BUTTON_PLAY_TO_PLAY
import com.maurodev.chronometer.utils.BUTTON_PLAY_TO_RESTART
import com.maurodev.chronometer.utils.BUTTON_STOP_TO_MODAL
import com.maurodev.chronometer.utils.BUTTON_STOP_TO_PLAY
import com.maurodev.chronometer.utils.StringUtils

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _vm: MainViewModel

    private val buttonStatus = ButtonStatus()
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

    private fun behaviorButtons() {
        _binding.bPlay.setOnClickListener {
            behaviorPlay()
        }

        _binding.bPause.setOnClickListener {
            behaviorPause()
        }

        _binding.bStop.setOnClickListener {
            behaviorStop()
        }
    }

    private fun behaviorPlay() {
        _binding.bPause.isEnabled = true
        buttonStatus.valueStop = BUTTON_STOP_TO_MODAL
        if (time == null) {
            buttonStatus.valuePlay = BUTTON_PLAY_TO_PLAY
            _vm.startChronometer()
            _binding.bPlay.text = BUTTON_LOOP_TEXT
        } else {
            buttonStatus.valuePlay = BUTTON_PLAY_TO_LOOP
            _vm.continueChronometer(BUTTON_PLAY_TO_LOOP)
            _binding.bPlay.text = BUTTON_LOOP_TEXT
        }
    }

    private fun behaviorPause() {
        buttonStatus.valuePlay = BUTTON_PLAY_TO_CONTINUE
        buttonStatus.valueStop = BUTTON_STOP_TO_MODAL
        _vm.pauseChronometer()
        _binding.bPause.isEnabled = false
        _binding.bPlay.text = BUTTON_CONTINUE_TEXT
    }

    private fun behaviorStop() {
        if (buttonStatus.valueStop == BUTTON_STOP_TO_MODAL) {
            //Show modal
            buttonStatus.valuePlay = BUTTON_PLAY_TO_RESTART
            buttonStatus.valuePause = BUTTON_PAUSE_TO_LOOP
            buttonStatus.valueStop = BUTTON_STOP_TO_PLAY
            Snackbar.make(_binding.root, "CONFIRM", Snackbar.LENGTH_SHORT).show()

            /**
             * TODO : modal de confirmacion y su implementacion.
             */
        }
    }
}
