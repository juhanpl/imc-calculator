package com.example.imccalculator

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.imccalculator.models.IMCResult
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider

class IMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        val cardMale = findViewById<CardView>(R.id.cardMale)
        var maleIsSelected: Boolean = false;
        val cardFemale = findViewById<CardView>(R.id.cardFemale)
        var femaleIsSelected: Boolean = false;

        cardMale.setOnClickListener {

            maleIsSelected = true
            if (femaleIsSelected)
            {
                femaleIsSelected = false
                cardFemale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cardColor))
            }
            cardMale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.selectCardColor))

        }

        cardFemale.setOnClickListener {

            femaleIsSelected = true
            if (maleIsSelected)
            {
                maleIsSelected = false
                cardMale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cardColor))
            }
            cardFemale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.selectCardColor))

        }

        val tvHeight = findViewById<TextView>(R.id.tvHeight)
        var height: Int = 120
        val slHeight = findViewById<Slider>(R.id.slHeight)

        slHeight.addOnChangeListener { slider, value, fromUser ->

            height = value.toInt()
            tvHeight.text = "${height} cm"

        }

        val tvWeight = findViewById<TextView>(R.id.tvWeight)
        var weight: Int = 90
        val btnSubstractWeight = findViewById<FloatingActionButton>(R.id.btnSubstractWeight)
        val btnPlusWeight = findViewById<FloatingActionButton>(R.id.btnPlusWeight)

        btnSubstractWeight.setOnClickListener {

            weight--
            if (weight < 0) weight = 0
            tvWeight.text = weight.toString()

        }

        btnPlusWeight.setOnClickListener {

            weight++
            if (weight < 0) weight = 0
            tvWeight.text = weight.toString()

        }

        val tvAge = findViewById<TextView>(R.id.tvAge)
        var age: Int = 30
        val btnSubstractAge = findViewById<FloatingActionButton>(R.id.btnSubstractAge)
        val btnPlusAge = findViewById<FloatingActionButton>(R.id.btnPlusAge)

        btnSubstractAge.setOnClickListener {

            age--
            if (age < 18) age = 18
            tvAge.text = age.toString()

        }

        btnPlusAge.setOnClickListener {

            age++
            if (age < 18) age = 18
            tvAge.text = age.toString()
        }

        val btnCalculate = findViewById<MaterialButton>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {

            if (!maleIsSelected && !femaleIsSelected) {

                Toast.makeText(this, "Seleccione un genero para continuar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            val imcResult = CalculateIMC(

                height = height,
                weight = weight,
                sex = if (maleIsSelected) {
                "Male"
                } else {
                    "Female"
                }
            )

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("EXTRA_IMC", imcResult)
            startActivity(intent)
            finish()


        }

    }

    fun CalculateIMC(height: Int, weight: Int, sex: String): IMCResult {

        val heightM = height / 100.0
        val imc = weight / (heightM * heightM)

        val type = when {

            sex == "Male" -> IMCMale(imc)
            sex == "Female" -> IMCFemale(imc)
            else -> "Can't calculate"

        }

        return IMCResult(type, imc)

    }

    fun IMCMale(imc: Double): String {

        return when {

            imc < 18.5 -> "Bajo peso"
            imc < 24.9 -> "Peso normal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Obesidad grado 1"
            imc < 40 -> "Obesidad grado 2"
            else -> "Obesidad morbida"

        }

    }

    fun IMCFemale(imc: Double): String {

        return when {

            imc < 18 -> "Bajo peso"
            imc < 24.5 -> "Peso normal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Obesidad grado 1"
            imc < 40 -> "Obesidad grado 2"
            else -> "Obesidad morbida"

        }

    }
}