package com.example.miprimerappp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerappp.models.IMCResult

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        @Suppress("DEPRECATION")
        val imcResult = intent.getParcelableExtra<IMCResult>("EXTRA_IMC")

        val tvIMCType = findViewById<TextView>(R.id.tvIMCType)
        val tvIMCResult = findViewById<TextView>(R.id.tvIMCResult)
        val tvIMCDescription = findViewById<TextView>(R.id.tvIMCDescription)

    }
}