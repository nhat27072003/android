package com.example.studentmanagerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class AddStudent : Fragment(R.layout.fragment_add_student) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameInput: EditText = view.findViewById(R.id.edtName)
        val mssvInput: EditText = view.findViewById(R.id.edtId)
        val addButton: Button = view.findViewById(R.id.btnAdd)

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val mssv = mssvInput.text.toString()
            if (name.isNotBlank() && mssv.isNotBlank()) {
                // Quay lại danh sách sinh viên với dữ liệu mới
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}