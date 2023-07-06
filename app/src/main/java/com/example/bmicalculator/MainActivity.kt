package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //get result upto 2 decimal places
                val bmi2dig = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2dig)
            }
        }
    }
    private fun validateInput(weight:String? , height : String?):Boolean   // ? is safe call operator
    {
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_LONG).show()
                false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is Empty",Toast.LENGTH_LONG).show()
                false
            }
            else ->{
                true
            }
        }
    }
    private fun displayResult ( bmi:Float)
    {
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val resultInfo = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        resultInfo.text = getString(R.string.normal_range_text)

        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText  = "You are UnderWeight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 ->{
                resultText = "You are Healthy!!"
                color = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultText = "You are Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                resultText = "You are Obes"
                color = R.color.obessed
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}