package com.maurodev.chronometer.ui.principal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maurodev.chronometer.R

class MainActivity : AppCompatActivity() {

    // TODO hacer binding
    lateinit var buttonPlay: Button
    lateinit var buttonStop: Button
    lateinit var buttonPause: Button
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_time_cicles)
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
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.adapter = adapter
    }
}