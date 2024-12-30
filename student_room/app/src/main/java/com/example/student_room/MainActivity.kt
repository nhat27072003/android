package com.example.student_room

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val studentViewModel: StudentViewModel by viewModels()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = StudentAdapter()
        recyclerViewStudents.layoutManager = LinearLayoutManager(this)
        recyclerViewStudents.adapter = adapter

        buttonAdd.setOnClickListener {
            val name = editTextName.text.toString()
            val mssv = editTextMSSV.text.toString()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                val student = Student(name = name, mssv = mssv)
                studentViewModel.insert(student)
                editTextName.text.clear()
                editTextMSSV.text.clear()
            }
        }

        studentViewModel.getAllStudents().observe(this) { students ->
            adapter.submitList(students)
        }
    }
}