package com.example.currency

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val txt1: EditText = findViewById(R.id.txt1)
        val txt2: EditText = findViewById(R.id.txt2)
        val spinner1: Spinner = findViewById(R.id.spiner1)
        val spinner2: Spinner = findViewById(R.id.spiner2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tỷ giá giữa các loại tiền tệ
        val exchangeRates = arrayOf(
            doubleArrayOf(1.0, 0.000034, 0.000042, 0.00032, 0.000041, 0.00015), // Vietnam-Dong
            doubleArrayOf(29480.0, 1.0, 1.2, 0.88, 0.12, 0.4),                // United Kingdom - Pound
            doubleArrayOf(23800.0, 0.83, 1.0, 0.74, 0.10, 0.33),              // United States - Dollar
            doubleArrayOf(31250.0, 1.13, 1.35, 1.0, 0.14, 0.45),              // Thailand - Baht
            doubleArrayOf(25000.0, 8.5, 10.0, 7.0, 1.0, 3.2),                 // Lao - Kip
            doubleArrayOf(6600.0, 2.5, 3.0, 2.2, 0.31, 1.0)                   // China - Yuan
        )

        var status = 1
        var spin1 = 0
        var spin2 = 0
        val items = listOf("Vietnam-Dong", "United Kingdom - Pound", "United States - Dollar", "Thailand - Baht", "Lao - Kip", "China - Yuan")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        spinner1.adapter = adapter
        spinner2.adapter = adapter


//        txt1.setOnClickListener {
//            txt1.setTypeface(null, Typeface.BOLD)
//            txt2.setTypeface(null, Typeface.NORMAL)
//            status = 1
//        }
//        txt2.setOnClickListener {
//            txt2.setTypeface(null, Typeface.BOLD)
//            txt1.setTypeface(null, Typeface.NORMAL)
//            status = 2
//        }

        fun updateTypeface() {
            if (status == 1) {
                txt1.setTypeface(null, Typeface.BOLD)
                txt2.setTypeface(null, Typeface.NORMAL)
            } else {
                txt2.setTypeface(null, Typeface.BOLD)
                txt1.setTypeface(null, Typeface.NORMAL)
            }
        }

// Gọi hàm này trong các sự kiện khác
        txt1.setOnClickListener {
            status = 1
            updateTypeface()
        }

        txt2.setOnClickListener {
            status = 2
            updateTypeface()
        }


        fun convertCurrency() {
            var amount = if (status == 1) {
                txt1.text.toString().toDoubleOrNull() ?: 0.0
            } else {
                txt2.text.toString().toDoubleOrNull() ?: 0.0
            }
            if(amount == null)
                amount = 0.0
            else {
                val convertAmount = if (status == 1) {
                    amount * exchangeRates[spin1][spin2]
                } else {
                    amount * exchangeRates[spin2][spin1]
                }
                if (status == 1) {
                    txt2.setText(String.format("%.2f", convertAmount))
                } else {
                    txt1.setText(String.format("%.2f", convertAmount))
                }
            }

        }

        // Listener cho spinner1
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                spin1 = position
                convertCurrency()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                spin1 = 0
            }
        }

        // Listener cho spinner2
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                spin2 = position
                convertCurrency()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                spin2 = 0
            }
        }

        // Hàm chuyển đổi tiền tệ


        // TextWatcher cho txt1
        txt1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (status == 1) {
                    convertCurrency()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // TextWatcher cho txt2
        txt2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (status == 2) {
                    convertCurrency()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
