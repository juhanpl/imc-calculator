package com.example.imccalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.imccalculator.models.IMCResult
import androidx.core.graphics.toColorInt
import com.google.android.material.button.MaterialButton

class ResultActivity : AppCompatActivity() {
    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        @Suppress("DEPRECATION")
        val imcResult = intent.getParcelableExtra<IMCResult>("EXTRA_IMC")!!

        val tvIMCType = findViewById<TextView>(R.id.tvIMCType)
        tvIMCType.setTextColor(getColorByIMC(imcResult.type))
        tvIMCType.text = imcResult.type

        val tvIMCResult = findViewById<TextView>(R.id.tvIMCResult)
        tvIMCResult.text = String.format("%.2f", imcResult.result)

        val tvIMCDescription = findViewById<TextView>(R.id.tvIMCDescription)
        tvIMCDescription.text = getIMCMessage(imcResult.type)

        val btnRecalculate = findViewById<MaterialButton>(R.id.btnRecalculate)
        btnRecalculate.setOnClickListener {

            val intent = Intent(this, IMCActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    fun getColorByIMC(type: String): Int {

        return when (type) {

            "Bajo peso" -> "#42A5F5".toColorInt()
            "Peso normal" -> "#66BB6A".toColorInt()
            "Sobrepeso" -> "#FFA726".toColorInt()
            "Obesidad grado 1" -> "#FB8C00".toColorInt()
            "Obesidad grado 2" -> "#E53935".toColorInt()
            "Obesidad morbida" -> "#B71C1C".toColorInt()
            else -> "#000000".toColorInt()


        }

    }

    fun getIMCMessage(type: String): String {
        return when (type) {

            "Bajo peso" ->
                "Necesitas mejorar tu nutrición para alcanzar un peso saludable."

            "Peso normal" ->
                "Mantén tus hábitos, estás en un rango saludable."

            "Sobrepeso" ->
                "Cuida tu alimentación y aumenta actividad física."

            "Obesidad grado 1" ->
                "Requiere mejorar dieta y actividad para evitar riesgos."

            "Obesidad grado 2" ->
                "Consulta un profesional y mejora tus hábitos urgentemente."

            "Obesidad morbida" ->
                "Riesgo alto: necesitas atención médica y cambios inmediatos."

            else ->
                "Sin datos suficientes."
        }
    }


}