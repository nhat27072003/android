package com.example.studentmanagerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView

class EditStudent : Fragment(R.layout.fragment_edit_student) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = EditStudent.fromBundle(requireArguments())
        val nameInput: EditText = view.findViewById(R.id.nameInput)
        val mssvInput: EditText = view.findViewById(R.id.mssvInput)
        val updateButton: Button = view.findViewById(R.id.updateButton)

        nameInput.setText(args.name)
        mssvInput.setText(args.mssv)

        updateButton.setOnClickListener {
            // Cập nhật thông tin
            findNavController().popBackStack()
        }
    }
}