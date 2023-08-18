package com.maurodev.chronometer.ui.principal

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.maurodev.chronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private var flagButtonPlay = false // Este valor debe vivir en el vm ya que no conserva el estado.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setupRecyclerView(_binding.root.context)
        behaviorButtons()
    }

    private fun setupRecyclerView(context: Context) {
        val adapter = RecyclerViewAdapter(
            mutableListOf(
                "1. 00 : 00 : 01 : 123",
                "2. 00 : 01 : 01 : 123",
                "3. 00 : 22 : 01 : 123",
                "4. 00 : 33 : 01 : 123",
                "1. 00 : 00 : 01 : 123",
                "2. 00 : 01 : 01 : 123",
                "3. 00 : 22 : 01 : 123",
                "4. 00 : 33 : 01 : 123"
            )
        )
        _binding.rvTimeCicles.layoutManager = LinearLayoutManager(context)
        _binding.rvTimeCicles.adapter = adapter
    }

    private fun behaviorButtons() {
        _binding.bPlay.setOnClickListener {
            flagButtonPlay = true
            behaviorButtonPlay()
        }

        _binding.bPause.setOnClickListener {
            flagButtonPlay = false
            behaviorButtonPlay()
        }

        _binding.bStop.setOnClickListener {
            flagButtonPlay = false
            behaviorButtonPlay()
        }
    }

    private fun behaviorButtonPlay() {
        _binding.bPlay.text = if (flagButtonPlay) LOOP else PLAY
    }
    companion object {
        const val LOOP = "Loop"
        const val PLAY = "Play"
    }
}