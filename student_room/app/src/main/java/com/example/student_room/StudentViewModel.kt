package com.example.student_room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : ViewModel() {
    private val studentDao: StudentDao = AppDatabase.getDatabase(application).studentDao()
    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents()

    fun addStudent(student: Student) {
        viewModelScope.launch {
            studentDao.insert(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            studentDao.update(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentDao.delete(student)
        }
    }
}