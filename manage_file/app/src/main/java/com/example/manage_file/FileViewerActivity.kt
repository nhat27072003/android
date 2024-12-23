package com.example.manage_file

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class FileViewerActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView = TextView(this)
        setContentView(textView)

        val filePath = intent.getStringExtra("filePath")
        filePath?.let {
            val file = File(it)
            if (file.exists()) {
                val content = file.readText()
                textView.text = content
            }
        }
    }
}