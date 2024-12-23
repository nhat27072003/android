package com.example.manage_file

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File
class FileAdapter(private val files: List<File>, private val listener: OnItemClickListener) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(file: File)
    }

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fileName: TextView = itemView.findViewById(R.id.file_name)
        val fileSize: TextView = itemView.findViewById(R.id.file_size)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(files[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        holder.fileName.text = file.name
        holder.fileSize.text = if (file.isDirectory) {
            "Folder"
        } else {
            "${file.length()} bytes"
        }
    }

    override fun getItemCount(): Int {
        return files.size
    }
}