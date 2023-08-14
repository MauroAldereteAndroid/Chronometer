package com.maurodev.chronometer.ui.principal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maurodev.chronometer.databinding.ActivityMainBinding

class PrincipalActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}