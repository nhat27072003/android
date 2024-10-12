package com.example.caculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.exp
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView;
    var stateCalculate = 0;
    var firstNumber = "";
    var secondNumber = "";
    var currentNumber = "";
    var operator = "";
    var expression = ""
    private lateinit var textEpression: TextView

    fun onNumberClick(view: View) {
        val button = view as Button
        if (button.text == "0" && (currentNumber == "" || currentNumber == "0"))
            currentNumber = "0"
        else currentNumber += button.text
        resultTextView.text = currentNumber
    }

    fun onCEClick(view: View) {
        currentNumber = "";
        resultTextView.text = "0"
    }

    fun onCClick(view: View) {
        firstNumber = ""
        secondNumber = ""
        operator = ""
        currentNumber = ""
        textEpression.text = ""
        resultTextView.text = "0"
    }

    fun onBSClick(view: View) {
        if (currentNumber != "") {
            currentNumber = currentNumber.substring(0, currentNumber.length - 1)

            if (currentNumber == "")
                currentNumber = "0"
            resultTextView.text = currentNumber.toString()
        }
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        operator = button.text.toString()

        if (currentNumber != "") {
            stateCalculate++
            if (stateCalculate == 1) {
                firstNumber = currentNumber
                currentNumber = ""
            } else {
                secondNumber = currentNumber
                val result = when (operator) {
                    "+" -> firstNumber.toDouble() + secondNumber.toDouble()
                    "-" -> firstNumber.toDouble() - secondNumber.toDouble()
                    "x" -> firstNumber.toDouble() * secondNumber.toDouble()
                    "/" -> firstNumber.toDouble() / secondNumber.toDouble()
                    else -> 0.0
                }

                resultTextView.text = result.toInt().toString()
                firstNumber = result.toString()
                currentNumber = ""
            }

        }
        expression = firstNumber + operator
        textEpression.text = expression.toString()
    }

    fun onEqualClick(view: View) {
        secondNumber = currentNumber
        currentNumber = ""
        val result = when (operator) {
            "+" -> firstNumber.toDouble() + secondNumber.toDouble()
            "-" -> firstNumber.toDouble() - secondNumber.toDouble()
            "x" -> firstNumber.toDouble() * secondNumber.toDouble()
            "/" -> firstNumber.toDouble() / secondNumber.toDouble()
            else -> 0.0
        }

        resultTextView.text = result.toInt().toString()
        expression = firstNumber + operator + secondNumber + "="
        textEpression.text = expression.toString()
        firstNumber = result.toString()
        stateCalculate = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textEpression = findViewById(R.id.txtExpression)
        resultTextView = findViewById(R.id.txtResult);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}