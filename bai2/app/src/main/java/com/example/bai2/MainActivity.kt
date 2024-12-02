package com.example.bai2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNumber = findViewById<EditText>(R.id.editText)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val showButton = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val txt1 = findViewById<TextView>(R.id.txt1)

        listView.visibility = View.GONE
        txt1.visibility = View.GONE

        showButton.setOnClickListener {
            val inputText = edtNumber.text.toString()
            if (inputText.isEmpty()) {
                txt1.text = "Vui lòng nhập số n"
                txt1.visibility = View.VISIBLE
                listView.visibility = View.GONE
                return@setOnClickListener
            }

            val n = inputText.toIntOrNull()
            if (n == null || n < 0) {
                txt1.text = "Vui lòng nhập số nguyên dương"
                txt1.visibility = View.VISIBLE
                listView.visibility = View.GONE
                return@setOnClickListener
            }

            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                txt1.text = "Vui lòng chọn một tùy chọn"
                txt1.visibility = View.VISIBLE
                listView.visibility = View.GONE
                return@setOnClickListener
            }

            val numbers = when (selectedId) {
                R.id.radioButton1 -> generateEvenNumbers(n)
                R.id.radioButton2 -> generateOddNumbers(n)
                R.id.radioButton3 -> generatePerfectSquares(n)
                else -> emptyList()
            }

            if (numbers.isNotEmpty()) {

                txt1.visibility = View.GONE
                listView.visibility = View.VISIBLE
                val adapter = ArrayAdapter(this, R.layout.item, R.id.textViewItem, numbers)
                listView.adapter = adapter
            } else {
                txt1.text = "Không có số nào phù hợp"
                txt1.visibility = View.VISIBLE
                listView.visibility = View.GONE
            }
        }
    }

    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun generateOddNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 != 0 }
    }

    private fun generatePerfectSquares(n: Int): List<Int> {
        return (0..n).filter { isPerfectSquare(it) }
    }

    private fun isPerfectSquare(number: Int): Boolean {
        val root = sqrt(number.toDouble()).toInt()
        return root * root == number
    }
}
