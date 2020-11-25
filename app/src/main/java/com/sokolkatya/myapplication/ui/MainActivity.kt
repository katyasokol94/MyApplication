package com.sokolkatya.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sokolkatya.myapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val tvMoveToNextScreen = findViewById<TextView>(R.id.tv_move_to_next_screen)
        tvMoveToNextScreen.setOnClickListener {
            val intent: Intent = Intent(this, MovieDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}