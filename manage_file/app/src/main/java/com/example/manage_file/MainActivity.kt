package com.example.manage_file

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity(), FileAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fileAdapter: FileAdapter
    private var currentPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        checkPermissions()

        currentPath = getExternalStorageDirectoryPath()
        Log.v("TAG", "Current path: $currentPath")
        loadFiles(currentPath)
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }
        } else {
            loadFiles(currentPath)
        }
    }

    private fun getExternalStorageDirectoryPath(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val dir = getExternalFilesDir(null) // Trả về thư mục của ứng dụng trong bộ nhớ ngoài
            dir?.absolutePath ?: ""
        } else {
            Environment.getExternalStorageDirectory().absolutePath // Dùng getExternalStorageDirectory() cho các phiên bản trước Android 10
        }
    }

    private fun loadFiles(path: String) {
        val directory = File(path)
        val files = directory.listFiles()?.toList() ?: emptyList()
        fileAdapter = FileAdapter(files, this)
        recyclerView.adapter = fileAdapter
    }

    override fun onItemClick(file: File) {
        if (file.isDirectory) {
            currentPath = file.absolutePath
            loadFiles(currentPath)
        } else if (file.isFile && file.extension == "txt") {
            val intent = Intent(this, FileViewerActivity::class.java)
            intent.putExtra("filePath", file.absolutePath)
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "không hỗ trợ định dạng khác txt", Toast.LENGTH_SHORT).show()        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadFiles(currentPath)
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
